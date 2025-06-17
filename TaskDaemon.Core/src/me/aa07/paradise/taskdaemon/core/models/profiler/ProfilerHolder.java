package me.aa07.paradise.taskdaemon.core.models.profiler;

import com.google.gson.annotations.SerializedName;

public class ProfilerHolder {
    // Need to use annotations here as checkstyle wont allow `round_id`
    @SerializedName("round_id")
    public int roundId;
    @SerializedName("profile_data")
    public String profilerData;
    @SerializedName("sendmaps_data")
    public String sendmapsData;
}
