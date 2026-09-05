package me.aa07.paradise.taskdaemon.core.models.github;

import com.google.gson.annotations.SerializedName;

public class GetConentsResponseModel {
    @SerializedName("content") // just incase GSON behaviour ever changes
    public String content;
}
