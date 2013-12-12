package subscriptions.mapping;

import subscriptions.Constants;
import subscriptions.systems.LAMP;
import subscriptions.systems.SAP_CRM;
import subscriptions.systems.Siebel;

/**
 * Transformation class.
 *
 * @author rassakhatsky
 */
public class Mapping {

    private boolean SAP_CRM_EMAIL;//Permission for SAP CRM - Email.
    private boolean SAP_CRM_SMS;//Permission for SAP CRM - SMS.

    private String connectionType;//Siebel Connection type

    private String z_LOY_NEWS_SUBSCR;//Новости М.Видео - Бонус - частота
    private String z_SUBSCR_NEWS;//Новости М.Видео - частота
    private String z_SUBSCR_NOVELTY;//Новинки М.Видео - частота
    private String z_SUBSCR_PROD_DAY;//Товар дня - частота
    private String z_SUBSCR_PROMO;//Акции М.Видео - частота

    private boolean status_Z_LOY_NEWS_SUBSCR;//Новости М.Видео - Бонус - статус
    private boolean status_Z_SUBSCR_NEWS;//Новости М.Видео - статус
    private boolean status_Z_SUBSCR_NOVELTY;//Новинки М.Видео - статус
    private boolean status_Z_SUBSCR_PROD_DAY;//Товар дня - статус
    private boolean status_Z_SUBSCR_PROMO;//Акции М.Видео - статус

    /**
     * Mapping from SAP CRM to Siebel (SMS).
     *
     * @param Siebel
     * @param sap
     * @return
     */
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
                for (boolean status : statuses) {
                    if (status) {
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

    /**
     * Mapping from SAP CRM to Siebel (Email).
     *
     * @param Siebel
     * @param sap
     * @return
     */
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

    /**
     * Mapping from Siebel to SAP CRM.
     *
     * @param Siebel
     * @param sap
     * @return
     */
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
            for (boolean status : statuses) {
                if (status) {
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

    /**
     * Mapping from LAMP to SAP CRM.
     *
     * @param Lamp
     * @param sap
     * @return
     */
    public SAP_CRM toCRM(LAMP Lamp, SAP_CRM sap) {
        this.getData(Lamp);
        String frequencies[] = {
            z_LOY_NEWS_SUBSCR,
            z_SUBSCR_NEWS,
            z_SUBSCR_NOVELTY,
            z_SUBSCR_PROD_DAY,
            z_SUBSCR_PROMO};
        boolean statuses[] = {
            status_Z_LOY_NEWS_SUBSCR,
            status_Z_SUBSCR_NEWS,
            status_Z_SUBSCR_NOVELTY,
            status_Z_SUBSCR_PROD_DAY,
            status_Z_SUBSCR_PROMO};

        int index = 0;
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            if (statuses[i]) {
                index += 1;
            }
            sap.setSubscription(Constants.SUBSCRIPTIONS[i], statuses[i], frequencies[i]);
        }
        sap.setPermissions(Constants.SAP_CRM_EMAIL, index > 0);
        return sap;
    }

    /**
     * Mapping from SAP CRM to LAMP.
     *
     * @param sap
     * @return
     */
    public LAMP toLAMP(SAP_CRM sap) {
        LAMP Lamp = new LAMP();
        this.getData(sap);
        String frequencies[] = {
            z_LOY_NEWS_SUBSCR,
            z_SUBSCR_NEWS,
            z_SUBSCR_NOVELTY,
            z_SUBSCR_PROD_DAY,
            z_SUBSCR_PROMO};
        boolean statuses[] = {
            status_Z_LOY_NEWS_SUBSCR,
            status_Z_SUBSCR_NEWS,
            status_Z_SUBSCR_NOVELTY,
            status_Z_SUBSCR_PROD_DAY,
            status_Z_SUBSCR_PROMO};

        if (SAP_CRM_EMAIL) {
            for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                Lamp.setSubscription(Constants.SUBSCRIPTIONS[i], statuses[i], frequencies[i]);
            }
        } else {
            for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
                Lamp.setSubscription(Constants.SUBSCRIPTIONS[i], false, frequencies[i]);
            }
        }
        return Lamp;
    }

    /**
     * Get data from CRM.
     *
     * @param sap
     */
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

    /**
     * Get data from Siebel.
     *
     * @param siebel
     */
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

    /**
     * Get data from LAMP.
     *
     * @param Lamp
     */
    private void getData(LAMP Lamp) {
        z_LOY_NEWS_SUBSCR = Lamp.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getFrequency();
        z_SUBSCR_NEWS = Lamp.getSubscription(Constants.Z_SUBSCR_NEWS).getFrequency();
        z_SUBSCR_NOVELTY = Lamp.getSubscription(Constants.Z_SUBSCR_NOVELTY).getFrequency();
        z_SUBSCR_PROD_DAY = Lamp.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getFrequency();
        z_SUBSCR_PROMO = Lamp.getSubscription(Constants.Z_SUBSCR_PROMO).getFrequency();

        status_Z_LOY_NEWS_SUBSCR = Lamp.getSubscription(Constants.Z_LOY_NEWS_SUBSCR).getStatus();
        status_Z_SUBSCR_NEWS = Lamp.getSubscription(Constants.Z_SUBSCR_NEWS).getStatus();
        status_Z_SUBSCR_NOVELTY = Lamp.getSubscription(Constants.Z_SUBSCR_NOVELTY).getStatus();
        status_Z_SUBSCR_PROD_DAY = Lamp.getSubscription(Constants.Z_SUBSCR_PROD_DAY).getStatus();
        status_Z_SUBSCR_PROMO = Lamp.getSubscription(Constants.Z_SUBSCR_PROMO).getStatus();
    }
}
