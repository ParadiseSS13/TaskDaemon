package me.aa07.paradise.taskdaemon.core.models.invision;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GetUserModel {
    @SerializedName("id") // just incase GSON behaviour ever changes
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("primaryGroup")
    public GroupHolder primaryGroup;
    @SerializedName("secondaryGroups")
    public List<GroupHolder> secondaryGroups;

    public class GroupHolder {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("formattedName")
        public String formattedName;
    }
}
