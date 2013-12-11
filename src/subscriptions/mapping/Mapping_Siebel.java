package subscriptions.mapping;

import subscriptions.Constants;
import subscriptions.systems.SAP_CRM;
import subscriptions.systems.Siebel;

/**
 *
 * @author rassakhatsky
 */
public class Mapping_Siebel {

    private boolean SAP_CRM_EMAIL;
    private boolean SAP_CRM_SMS;

    private String connectionType;

    private String z_LOY_NEWS_SUBSCR;
    private String z_SUBSCR_NEWS;
    private String z_SUBSCR_NOVELTY;
    private String z_SUBSCR_PROD_DAY;
    private String z_SUBSCR_PROMO;

    private boolean status_Z_LOY_NEWS_SUBSCR;
    private boolean status_Z_SUBSCR_NEWS;
    private boolean status_Z_SUBSCR_NOVELTY;
    private boolean status_Z_SUBSCR_PROD_DAY;
    private boolean status_Z_SUBSCR_PROMO;

    public Siebel toSiebel_SMS(Siebel Siebel, SAP_CRM sap) {
        connectionType = Siebel.getConnectionType();
        this.getData(sap);
        boolean statuses[] = {
            status_Z_LOY_NEWS_SUBSCR,
            status_Z_SUBSCR_NEWS,
            status_Z_SUBSCR_NOVELTY,
            status_Z_SUBSCR_PROD_DAY,
            status_Z_SUBSCR_PROMO};
        String frequencies[] = {
            z_LOY_NEWS_SUBSCR,
            z_SUBSCR_NEWS,
            z_SUBSCR_NOVELTY,
            z_SUBSCR_PROD_DAY,
            z_SUBSCR_PROMO};
        if (SAP_CRM_SMS) {
            Siebel.setConnectionType(Constants.SIEBEL_SMS);
        } else {
            if (Siebel.getConnectionType().equals(Constants.SIEBEL_SMS)) {
                int permission4email = 0;
                for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                    if (statuses[i]) {
                        permission4email += 1;
                    }
                }
                if (permission4email > 0) {
                    Siebel.setConnectionType(Constants.SIEBEL_EMAIL);
                } else {
                    Siebel.setConnectionType(Constants.SIEBEL_NO);
                }
            }
        }
        return Siebel;
    }

    public Siebel toSiebel_Email(Siebel Siebel, SAP_CRM sap) {
        connectionType = Siebel.getConnectionType();
        this.getData(sap);
        boolean statuses[] = {
            status_Z_LOY_NEWS_SUBSCR,
            status_Z_SUBSCR_NEWS,
            status_Z_SUBSCR_NOVELTY,
            status_Z_SUBSCR_PROD_DAY,
            status_Z_SUBSCR_PROMO};
        String frequencies[] = {
            z_LOY_NEWS_SUBSCR,
            z_SUBSCR_NEWS,
            z_SUBSCR_NOVELTY,
            z_SUBSCR_PROD_DAY,
            z_SUBSCR_PROMO};
        if (SAP_CRM_EMAIL) {
            if (!connectionType.equals(Constants.SIEBEL_SMS)) {
                Siebel.setConnectionType(Constants.SIEBEL_EMAIL);
            }
        } else {
            if (connectionType.equals(Constants.SIEBEL_EMAIL)) {
                Siebel.setConnectionType(Constants.SIEBEL_NO);
            }
            for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                statuses[i] = false;
            }
        }
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            Siebel.setSubscription(Constants.SUBSCRIPTIONS[i], statuses[i], frequencies[i]);
        }
        return Siebel;
    }

    public SAP_CRM toCRM(Siebel Siebel, SAP_CRM sap) {
        this.getData(Siebel);
        boolean statuses[] = {
            status_Z_LOY_NEWS_SUBSCR,
            status_Z_SUBSCR_NEWS,
            status_Z_SUBSCR_NOVELTY,
            status_Z_SUBSCR_PROD_DAY,
            status_Z_SUBSCR_PROMO};
        String frequencies[] = {
            z_LOY_NEWS_SUBSCR,
            z_SUBSCR_NEWS,
            z_SUBSCR_NOVELTY,
            z_SUBSCR_PROD_DAY,
            z_SUBSCR_PROMO};

        sap.setPermissions(Constants.SAP_CRM_EMAIL, true);
        sap.setPermissions(Constants.SAP_CRM_SMS, true);

        if (connectionType.equals(Constants.SIEBEL_NO)) {
            sap.setPermissions(Constants.SAP_CRM_SMS, false);
            sap.setPermissions(Constants.SAP_CRM_EMAIL, false);
            for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                statuses[i] = false;
            }
        } else {
            if (!connectionType.equals(Constants.SIEBEL_NONE) && (!connectionType.equals(Constants.SIEBEL_SMS))) {
                sap.setPermissions(Constants.SAP_CRM_SMS, false);
            }

            int permission4email = 0;
            for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                if (statuses[i]) {
                    permission4email += 1;
                }
            }
            if (permission4email == 0) {
                sap.setPermissions(Constants.SAP_CRM_EMAIL, false);
            }
        }
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            sap.setSubscription(Constants.SUBSCRIPTIONS[i], statuses[i], frequencies[i]);
        }
        return sap;
    }

    private void getData(SAP_CRM sap) {
        SAP_CRM_EMAIL = sap.getPermissions(Constants.SAP_CRM_EMAIL);
        SAP_CRM_SMS = sap.getPermissions(Constants.SAP_CRM_SMS);

        z_LOY_NEWS_SUBSCR = sap.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getFrequency();
        z_SUBSCR_NEWS = sap.getSubscription(Constants.Z_SUBSCR_NEWS).getFrequency();
        z_SUBSCR_NOVELTY = sap.getSubscription(Constants.Z_SUBSCR_NOVELTY).getFrequency();
        z_SUBSCR_PROD_DAY = sap.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getFrequency();
        z_SUBSCR_PROMO = sap.getSubscription(Constants.Z_SUBSCR_PROMO).getFrequency();

        status_Z_LOY_NEWS_SUBSCR = sap.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getStatus();
        status_Z_SUBSCR_NEWS = sap.getSubscription(Constants.Z_SUBSCR_NEWS).getStatus();
        status_Z_SUBSCR_NOVELTY = sap.getSubscription(Constants.Z_SUBSCR_NOVELTY).getStatus();
        status_Z_SUBSCR_PROD_DAY = sap.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getStatus();
        status_Z_SUBSCR_PROMO = sap.getSubscription(Constants.Z_SUBSCR_PROMO).getStatus();
    }

    private void getData(Siebel siebel) {
        connectionType = siebel.getConnectionType();

        z_LOY_NEWS_SUBSCR = siebel.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getFrequency();
        z_SUBSCR_NEWS = siebel.getSubscription(Constants.Z_SUBSCR_NEWS).getFrequency();
        z_SUBSCR_NOVELTY = siebel.getSubscription(Constants.Z_SUBSCR_NOVELTY).getFrequency();
        z_SUBSCR_PROD_DAY = siebel.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getFrequency();
        z_SUBSCR_PROMO = siebel.getSubscription(Constants.Z_SUBSCR_PROMO).getFrequency();

        status_Z_LOY_NEWS_SUBSCR = siebel.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getStatus();
        status_Z_SUBSCR_NEWS = siebel.getSubscription(Constants.Z_SUBSCR_NEWS).getStatus();
        status_Z_SUBSCR_NOVELTY = siebel.getSubscription(Constants.Z_SUBSCR_NOVELTY).getStatus();
        status_Z_SUBSCR_PROD_DAY = siebel.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getStatus();
        status_Z_SUBSCR_PROMO = siebel.getSubscription(Constants.Z_SUBSCR_PROMO).getStatus();
    }
}