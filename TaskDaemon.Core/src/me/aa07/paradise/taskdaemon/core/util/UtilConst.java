package me.aa07.paradise.taskdaemon.core.util;

import com.google.gson.Gson;
import java.util.UUID;

public class UtilConst {
    public static final UUID BYOND_OAUTH_SERVICE_ID = UUID.fromString("d7a7b659-69bd-4435-b585-3b4ba97ca076");
    public static final UUID DISCORD_OAUTH_SERVICE_ID = UUID.fromString("bc9fb785-6a1f-434e-80da-26a37450a29a");
    public static final UUID INVISION_OAUTH_SERVICE_ID = UUID.fromString("4634c7d7-2bad-4139-a05f-13f296b20af8");
    public static final UUID PATREON_OAUTH_SERVICE_ID = UUID.fromString("626f4935-d046-4957-9862-f26102e27a70");

    public static final UUID AUTHENTIK_GROUP_KIBANAACCESS = UUID.fromString("8687394e-ad12-45a5-bba8-86b1e82d3daf");

    public static final int INVISION_INGAMEVERIFIED_GID = 30;
    public static final int INVISION_INGAMEBANNED_GID = 31;

    public static final int INGAME_RANK_CM = 99;
    public static final int INGAME_RANK_DEV = 100;
    public static final int INGAME_RANK_GA = 101;
    public static final int INGAME_RANK_HOS = 102;
    public static final int INGAME_RANK_HC = 103;
    public static final int INGAME_RANK_MENTOR = 104;
    public static final int INGAME_RANK_REMOVED = 105;
    public static final int INGAME_RANK_SDEV = 106;
    public static final int INGAME_RANK_SHOST = 107;
    public static final int INGAME_RANK_TA = 108;

    public static final Gson GSON = new Gson();
}
