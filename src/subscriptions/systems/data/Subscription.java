package subscriptions.systems.data;

/**
 * Subscriptions.
 *
 * @author rassakhatsky
 */
public class Subscription {

    private String subscriptionID; //Subscription ID
    private boolean status; //Subscription status: true - active, false - not active
    private String frequency; //Subscription frequency

    /**
     * Set new subscription.
     * @param subscriptionID - subscription ID.
     * @param status - subscription status.
     * @param frequency - subscription frequency.
     * @return Subscription class.
     */
    public Subscription setSubscription(String subscriptionID, boolean status, String frequency) {
        this.setSubscriptionID(subscriptionID);
        this.setStatus(status);
        this.setFrequency(frequency);
        return this;
    }

    public void setSubscriptionID(final String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }

    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public String getFrequency() {
        return frequency;
    }

    public boolean getStatus() {
        return status;
    }
}
