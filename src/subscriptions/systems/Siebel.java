package subscriptions.systems;

import java.util.ArrayList;
import subscriptions.systems.data.Subscription;

/**
 *
 * @author rassakhatsky
 */
public class Siebel {

    private String connectionType;
    private final ArrayList<Subscription> subscriptions = new ArrayList<>();

    /**
     * Set connection type.
     *
     * @param connectionType
     */
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    /**
     * Get connection type.
     *
     * @return connecton type
     */
    public String getConnectionType() {
        return this.connectionType;
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
}
