package me.aa07.paradise.taskdaemon.core.models.patreon;

import java.util.ArrayList;

public class PatreonOuterResponseModel {
    // Yes - you need both of these
    public ArrayList<PatreonRawResponseModel> data = new ArrayList<PatreonRawResponseModel>();
    public ArrayList<PatreonRawResponseModel> included = new ArrayList<PatreonRawResponseModel>();
}
