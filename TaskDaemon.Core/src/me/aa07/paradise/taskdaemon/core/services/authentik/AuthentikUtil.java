package me.aa07.paradise.taskdaemon.core.services.authentik;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;
import me.aa07.paradise.taskdaemon.core.config.AuthentikConfig;
import me.aa07.paradise.taskdaemon.core.models.authentik.ModifyGroupRequestModel;
import me.aa07.paradise.taskdaemon.core.util.UtilConst;

public class AuthentikUtil {
    private AuthentikConfig config;
    private HttpClient httpClient;

    public AuthentikUtil(AuthentikConfig config) {
        this.config = config;
        httpClient = HttpClient.newHttpClient();
    }

    public boolean addUserToGroup(int authentikId, UUID groupId) {
        return modifyGroup(authentikId, groupId, true);
    }

    public boolean removeUserFromGroup(int authentikId, UUID groupId) {
        return modifyGroup(authentikId, groupId, false);
    }

    private boolean modifyGroup(int authentikId, UUID groupId, boolean add) {
        String operation = add ? "add_user" : "remove_user";
        String add_group_url = String.format("v3/core/groups/%s/%s/", groupId.toString(), operation);
        String add_group_full_url = String.format("%s/%s", config.apiRoot, add_group_url);

        // This needs a POST body
        ModifyGroupRequestModel agrm = new ModifyGroupRequestModel();
        agrm.userId = authentikId;
        String add_group_body = UtilConst.GSON.toJson(agrm);

        // Create the request
        try {
            BodyPublisher add_group_bp = HttpRequest.BodyPublishers.ofString(add_group_body);
            HttpRequest add_group_req = HttpRequest.newBuilder().uri(new URI(add_group_full_url)).POST(add_group_bp)
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", String.format("Bearer %s", config.apiKey)).build();

            HttpResponse<String> add_group_res = httpClient.send(add_group_req, BodyHandlers.ofString());
            return add_group_res.statusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
