package subscriptions.systems;

import java.util.ArrayList;
import subscriptions.systems.data.Subscription;

/**
 * LAMP profile.
 *
 * @author rassakhatsky
 */
public class LAMP implements System{

    private final ArrayList<Subscription> subscriptions = new ArrayList<>(); //Subcriptions list.

    /**
     * Set new subscription.
     *
     * @param subscriptionID
     * @param status
     * @param frequency
     */
    @Override
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
    @Override
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
