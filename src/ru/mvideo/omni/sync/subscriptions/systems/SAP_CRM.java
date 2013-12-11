package ru.mvideo.omni.sync.subscriptions.systems;

import ru.mvideo.omni.sync.subscriptions.Constants;
import java.util.ArrayList;
import ru.mvideo.omni.sync.subscriptions.systems.data.Subscription;

/**
 *
 * @author rassakhatsky
 */
public class SAP_CRM {

    private final ArrayList<Subscription> subscription = new ArrayList<>();
    private final SAP_Permissions SMS_Permission = new SAP_Permissions();
    private final SAP_Permissions EMAIL_Permission = new SAP_Permissions();

    public void setPermissions(String type, boolean permission) {
        if (type.equals(Constants.SAP_CRM_SMS)) {
            SMS_Permission.setPermission(type, permission);
        }
        if (type.equals(Constants.SAP_CRM_EMAIL)) {
            EMAIL_Permission.setPermission(type, permission);
        }
    }

    public boolean getPermissions(String type) {
        boolean result;
        if (type.equals(Constants.SAP_CRM_SMS)) {
            result = this.SMS_Permission.getPermission();
        } else {
            result = this.EMAIL_Permission.getPermission();
        }
        return result;
    }

    public void setSubscription(String subscriptionID, boolean status, String frequency) {
        if (subscription.size() > 0) {
            boolean subscription_not_exists = true;
            for (int i = 0; i < subscription.size(); i++) {
                if (subscription.get(i).getSubscriptionID().equals(subscriptionID)) {
                    subscription_not_exists = false;
                    subscription.get(i).setFrequency(frequency);
                    subscription.get(i).setStatus(status);
                }
            }
            if (subscription_not_exists) {
                subscription.add(new Subscription().setSubscription(subscriptionID, status, frequency));
            }
        } else {
            subscription.add(new Subscription().setSubscription(subscriptionID, status, frequency));
        }
    }

    public Subscription getSubscription(String subscriptionID) {
        int index = 0;
        for (int i = 0; i < subscription.size(); i++) {
            if (subscription.get(i).getSubscriptionID().equals(subscriptionID)) {
                index = i;
            }
        }
        return this.subscription.get(index);
    }

    private class SAP_Permissions {

        private String channel;
        private boolean permission;

        public void setPermission(String type, boolean permission) {
            this.permission = permission;
        }

        public boolean getPermission() {
            return this.permission;
        }
    }
}
