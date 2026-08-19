package me.aa07.paradise.taskdaemon.core.models.patreon;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// This entire thing is just because the Patreon API schema is about as loose as a joke I cant make here
public class PatreonRawResponseModel {
    public String id; // This can be a GUID for a member relation ID, or an ID for a tier. No, I am not joking
    public String type; // Type - we only care about "member", tiers are hardcoded
    public AttributesHolder attributes; // Used for getting tier amount in cents, and a users payment
    public RelationshipsHolder relationships; // Used to get a user ID and what tier they are

    public PatreonUser asUser() {
        PatreonUser retval = new PatreonUser();

        retval.userId = Integer.parseInt(relationships.user.data.id);

        for (GenericDataHolder gdh : relationships.currentlyEntitledTiers.data) {
            retval.tierIds.add(Integer.valueOf(gdh.id));
        }

        retval.amountInCents = attributes.currentlyEntitledAmountCents;

        return retval;
    }

    public class AttributesHolder {
        // USER PROPERTIES
        @SerializedName("currently_entitled_amount_cents")
        public int currentlyEntitledAmountCents;

        @SerializedName("patron_status")
        public String patronStatus;
    }

    public class RelationshipsHolder {
        @SerializedName("currently_entitled_tiers")
        public CurrentlyEntitledTiersHolder currentlyEntitledTiers;

        public UserHolder user;
    }


    public class CurrentlyEntitledTiersHolder {
        public List<GenericDataHolder> data;
    }

    public class GenericDataHolder {
        public String id;
        public String type;
    }

    public class UserHolder {
        public GenericDataHolder data;
    }
}
