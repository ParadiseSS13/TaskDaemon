package me.aa07.paradise.taskdaemon.core.models.ip2asn;

import com.google.gson.annotations.SerializedName;

public class Ip2AsnResponseModel {
    @SerializedName("as_number") // Cant use underscores in class fields - checkstyle gets unhappy
    public int asn;
}
