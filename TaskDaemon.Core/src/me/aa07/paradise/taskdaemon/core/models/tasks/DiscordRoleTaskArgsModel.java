package me.aa07.paradise.taskdaemon.core.models.tasks;

import com.google.gson.annotations.SerializedName;

public class DiscordRoleTaskArgsModel {
    @SerializedName("discordId") // just incase GSON behaviour ever changes
    public long discordId;
}
