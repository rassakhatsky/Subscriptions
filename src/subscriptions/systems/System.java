package subscriptions.systems;

import subscriptions.systems.data.Subscription;

/**
 *
 * @author rassakhatsky
 */
public interface System {

    void setSubscription(String subscriptionID, boolean status, String frequency);

    Subscription getSubscription(String subscriptionID);
}
