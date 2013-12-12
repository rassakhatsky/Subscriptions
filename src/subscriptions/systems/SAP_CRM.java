package subscriptions.systems;

import subscriptions.Constants;
import java.util.ArrayList;
import subscriptions.systems.data.Subscription;

/**
 * SAP CRM Class.
 *
 * @author rassakhatsky
 */
public class SAP_CRM {

    private final ArrayList<Subscription> subscriptions = new ArrayList<>();//Subscriptions list
    private final SAP_Permissions SMS_Permission = new SAP_Permissions();//SMS permisson
    private final SAP_Permissions EMAIL_Permission = new SAP_Permissions();//Email permisson

    /**
     * Set permission
     *
     * @param type - permission type
     * @param permission true-active, false - not active
     */
    public void setPermissions(String type, boolean permission) {
        if (type.equals(Constants.SAP_CRM_SMS)) {
            SMS_Permission.setPermission(type, permission);
        }
        if (type.equals(Constants.SAP_CRM_EMAIL)) {
            EMAIL_Permission.setPermission(type, permission);
        }
    }

    /**
     * Get permission.
     *
     * @param type - permission type
     * @return true-active, false - not active
     */
    public boolean getPermissions(String type) {
        boolean result;
        if (type.equals(Constants.SAP_CRM_SMS)) {
            result = this.SMS_Permission.getPermission();
        } else {
            result = this.EMAIL_Permission.getPermission();
        }
        return result;
    }

    /**
     * Set new subscription.
     *
     * @param subscriptionID
     * @param status
     * @param frequency
     */
    public void setSubscription(String subscriptionID, boolean status, String frequency) {
        if (subscriptions.size() > 0) { //Check if list empty
            boolean subscription_not_exists = true;
            for (Subscription subs : subscriptions) {
                if (subs.getSubscriptionID().equals(subscriptionID)) {
                    subscription_not_exists = false;
                    subs.setFrequency(frequency);
                    subs.setStatus(status);
                }
            }
            if (subscription_not_exists) {
                subscriptions.add(new Subscription().setSubscription(subscriptionID, status, frequency));
            }
        } else {
            subscriptions.add(new Subscription().setSubscription(subscriptionID, status, frequency));
        }
    }

    /**
     * Get subscription class by it's ID.
     *
     * @param subscriptionID
     * @return
     */
    public Subscription getSubscription(String subscriptionID) {
        Subscription result = null;
        for (Subscription subs : subscriptions) {
            if (subs.getSubscriptionID().equals(subscriptionID)) {
                result = subs;
            }
        }
        return result;
    }

    /**
     * SAP ERP Permissions.
     */
    private class SAP_Permissions {

        private String channel;
        private boolean permission;

        /**
         * set new permission
         *
         * @param type
         * @param permission
         */
        public void setPermission(String type, boolean permission) {
            this.permission = permission;
        }

        /**
         * get permission data.
         *
         * @return
         */
        public boolean getPermission() {
            return this.permission;
        }
    }
}
