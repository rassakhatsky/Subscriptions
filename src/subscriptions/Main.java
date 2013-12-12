package subscriptions;

import subscriptions.mapping.Mapping;
import subscriptions.systems.LAMP;
import subscriptions.systems.SAP_CRM;
import subscriptions.systems.Siebel;

/**
 *
 * @author rassakhatsky
 */
public class Main {

    public SAP_CRM crm_client;//SAP CRM
    public Siebel siebel;//Oracle Siebel CRM
    public LAMP lamp;//LAMP
    
    /**
     * Synchronization SAP CRM.
     * @param sms SMS synchronization
     * @param email Email synchronization
     */
    public void fromCRM(final boolean sms, final boolean email) {
        if (email) {
            this.syncCRM_Email();
        }
        if (sms) {
            this.syncCRM_SMS();
        }
    }

    /**
     * Synchronization LAMP.
     */
    public void toCRM_LAMP() {
        Mapping mapping = new Mapping();
        crm_client = mapping.toCRM(lamp, crm_client);
    }

    /**
     * Synchronization Siebel.
     */
    public void toCRM_Siebel() {
        Mapping mapping = new Mapping();
        crm_client = mapping.toCRM(siebel, crm_client);
    }

    /**
     * Synchronization SAP CRM (Email).
     */
    private void syncCRM_Email() {
        Mapping mapping_Lamp = new Mapping();
        lamp = mapping_Lamp.toLAMP(crm_client);

        Mapping mapping_Siebel = new Mapping();
        siebel = mapping_Siebel.toSiebel_Email(siebel, crm_client);
    }

    /**
     * Synchronization SAP CRM (SMS).
     */
    private void syncCRM_SMS() {
        Mapping mapping = new Mapping();
        siebel = mapping.toSiebel_SMS(siebel, crm_client);
    }
    
    /**
     * Set initial data for web form.
     */
    public void setInitialData() {
        crm_client = new SAP_CRM();
        siebel = new Siebel();
        lamp = new LAMP();

        /**
         * Set CRM Data.
         */
        crm_client.setPermissions(Constants.SAP_CRM_SMS, true);

        crm_client.setPermissions(Constants.SAP_CRM_EMAIL, true);
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            crm_client.setSubscription(Constants.SUBSCRIPTIONS[i], true, Constants.TWO_MON);
        }

        /**
         * Set Siebel Data.
         */
        siebel.setConnectionType(Constants.SIEBEL_SMS);
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            siebel.setSubscription(Constants.SUBSCRIPTIONS[i], true, Constants.TWO_MON);
        }

        /**
         * Set LAMP Data.
         */
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            lamp.setSubscription(Constants.SUBSCRIPTIONS[i], true, Constants.TWO_MON);
        }
    }
}
