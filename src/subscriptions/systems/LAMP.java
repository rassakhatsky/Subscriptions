package subscriptions.systems;

import java.util.ArrayList;
import subscriptions.systems.data.Subscription;

/**
 *
 * @author rassakhatsky
 */
public class LAMP {

    private final ArrayList<Subscription> subscription = new ArrayList<>();

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
}