package me.aa07.paradise.taskdaemon.core.services.invision;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import me.aa07.paradise.taskdaemon.core.config.InvisionConfig;
import me.aa07.paradise.taskdaemon.core.models.invision.GetUserModel;
import me.aa07.paradise.taskdaemon.core.models.invision.UpdateSecondaryGroupsModel;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;
import org.apache.commons.lang3.tuple.Pair;

public class InvisionUtil {
    private HttpClient httpClient;
    private InvisionConfig invisionConfig;

    public InvisionUtil(InvisionConfig invisionConfig) {
        this.invisionConfig = invisionConfig;
        httpClient = HttpClient.newHttpClient();
    }

    private Pair<String, Integer> makeInvisionRequest(String uri, HttpRequestMethod reqType, String bodyContent) throws Exception {
        // Step 1 - Make our base
        Builder request_builder = HttpRequest.newBuilder().uri(new URI(String.format("%s/%s", invisionConfig.apiBaseUrl, uri)));

        switch (reqType) {
            case Get:
                request_builder = request_builder.GET();
                break;
            case Post:
                if (bodyContent == null) {
                    request_builder = request_builder.POST(HttpRequest.BodyPublishers.noBody());
                } else {
                    request_builder = request_builder.POST(HttpRequest.BodyPublishers.ofString(bodyContent));
                }
                break;
            default:
                throw new Exception(String.format("Request type %s not supported", reqType.toString()));
        }

        // Generate auth header
        String creds = String.format("%s:", invisionConfig.apiKey);
        byte[] encoded_auth = Base64.getEncoder().encode(creds.getBytes(StandardCharsets.UTF_8));
        String auth_header = String.format("Basic %s", new String(encoded_auth));

        HttpRequest final_request = request_builder.setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .setHeader("Authorization", auth_header).build();

        HttpResponse<String> response = httpClient.send(final_request, BodyHandlers.ofString());

        return Pair.of(response.body(), response.statusCode());
    }

    public Pair<Boolean, List<Integer>> getUserSecondaryGroups(int userId) {
        ArrayList<Integer> out_list = new ArrayList<Integer>();
        String request_uri = String.format("core/members/%s", userId);

        Pair<String, Integer> invision_response = null;

        // Send it off
        try {
            invision_response = makeInvisionRequest(request_uri, HttpRequestMethod.Get, null);
        } catch (Exception e) {
            e.printStackTrace();
            return Pair.of(false, out_list);
        }

        // Shouldnt ever happen
        if (invision_response == null || invision_response.getRight() != 200) {
            return Pair.of(false, out_list);
        }

        // Try decode
        GetUserModel gum = UtilConst.GSON.fromJson(invision_response.getLeft(), GetUserModel.class);

        if (gum == null) {
            return Pair.of(false, out_list);
        }

        // This one does return true - they could have 0 secondary groups
        if (gum.secondaryGroups == null) {
            return Pair.of(true, out_list);
        }

        // If we are here we can decode
        for (GetUserModel.GroupHolder gh : gum.secondaryGroups) {
            out_list.add(gh.id);
        }

        return Pair.of(true, out_list);
    }

    public boolean updateUserSecondaryGroups(int userId, List<Integer> secondaryGroups) {
        String request_uri = String.format("core/members/%s", userId);
        UpdateSecondaryGroupsModel usgm = new UpdateSecondaryGroupsModel();
        usgm.secondaryGroups = secondaryGroups;

        String request_body = UtilConst.GSON.toJson(usgm);
        Pair<String, Integer> invision_response = null;

        // Send it off
        try {
            invision_response = makeInvisionRequest(request_uri, HttpRequestMethod.Post, request_body);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // Shouldnt ever happen
        if (invision_response == null || invision_response.getRight() != 200) {
            return false;
        }

        return true;
    }
}
