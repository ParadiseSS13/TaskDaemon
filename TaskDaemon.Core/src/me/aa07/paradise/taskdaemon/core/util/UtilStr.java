package me.aa07.paradise.taskdaemon.core.util;

import java.util.regex.Pattern;

public class UtilStr {
    private static final Pattern CKEY_PATTERN = Pattern.compile("[^a-z0-9]");

    public static String cleanCkey(String ckey) {
        // If blank got passed, dont do it
        if (ckey == null) {
            return "";
        }
        
        // Lowercase it
        ckey = ckey.toLowerCase();

        // Clean these out from Authentik
        ckey = ckey.replace("user:", "");


        // Split anything else out
        return CKEY_PATTERN.matcher(ckey).replaceAll("");
    }
}
