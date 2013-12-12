package subscriptions;

import subscriptions.mapping.Mapping_Lamp;
import subscriptions.mapping.Mapping_Siebel;
import subscriptions.systems.LAMP;
import subscriptions.systems.SAP_CRM;
import subscriptions.systems.Siebel;

/**
 *
 * @author rassakhatsky
 */
public class Mapping {

    public SAP_CRM crm_client;
    public Siebel siebel;
    public LAMP lamp;

    public void fromCRM(String type) {
        if (type.equalsIgnoreCase("email")) {
            Mapping_Lamp mapping_Lamp = new Mapping_Lamp();
            lamp = mapping_Lamp.toLAMP(crm_client);

            Mapping_Siebel mapping_Siebel = new Mapping_Siebel();
            siebel = mapping_Siebel.toSiebel_Email(siebel, crm_client);
        } else {
            Mapping_Siebel mapping_Siebel = new Mapping_Siebel();
            siebel = mapping_Siebel.toSiebel_SMS(siebel, crm_client);
        }
    }

    public void toCRM_LAMP() {
        Mapping_Lamp mapping_Lamp = new Mapping_Lamp();
        crm_client = mapping_Lamp.toCRM(lamp, crm_client);
    }

    public void toCRM_Siebel() {
        Mapping_Siebel mapping_Siebel = new Mapping_Siebel();
        crm_client = mapping_Siebel.toCRM(siebel, crm_client);
    }

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
