package me.aa07.paradise.taskdaemon.core.models.patreon;

import java.util.ArrayList;

public class PatreonUser {
    public int userId;
    public ArrayList<Integer> tierIds = new ArrayList<Integer>();
    public int amountInCents;

    public ArrayList<ParsedTier> parsedTiers() {
        ArrayList<ParsedTier> retval = new ArrayList<ParsedTier>();

        for (int tid : tierIds) {
            for (ParsedTier pt : ParsedTier.values()) {
                if (pt.tierId == tid) {
                    retval.add(pt);
                    break;
                }
            }
        }

        return retval;
    }

    public ParsedTier maxTier() {
        ParsedTier max_tier = null;
        for (ParsedTier pt : parsedTiers()) {
            if (max_tier == null || max_tier.tierLevel < pt.tierLevel) {
                max_tier = pt;
            }
        }

        return max_tier;
    }

    public enum ParsedTier {
        Unset(10405936, 0, 0),
        Supporter(1109955, 100, 1),
        Icon(969147, 500, 2),
        Loadout(1024004, 1000, 3),
        Silver(3644214, 1500, 4),
        Gold(1024003, 2000, 5);

        public int tierId;
        public int amountInCents;
        public int tierLevel;

        private ParsedTier(int tierId, int amountInCents, int tierLevel) {
            this.tierId = tierId;
            this.amountInCents = amountInCents;
            this.tierLevel = tierLevel;
        }
    }
}
