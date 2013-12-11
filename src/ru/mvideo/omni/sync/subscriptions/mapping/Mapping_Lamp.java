package ru.mvideo.omni.sync.subscriptions.mapping;

import ru.mvideo.omni.sync.subscriptions.Constants;
import ru.mvideo.omni.sync.subscriptions.systems.LAMP;
import ru.mvideo.omni.sync.subscriptions.systems.SAP_CRM;

/**
 *
 * @author rassakhatsky
 */
public class Mapping_Lamp {

    private boolean SAP_CRM_EMAIL;
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

        int status = 0;
        for (int i = 0; i < Constants.SUBSCRIPTIONS.length; i++) {
            if (statuses[i]) {
                status += 1;
            }
            sap.setSubscription(Constants.SUBSCRIPTIONS[i], statuses[i], frequencies[i]);
        }
        sap.setPermissions(Constants.SAP_CRM_EMAIL, status > 0);
        return sap;
    }

    private void getData(SAP_CRM sap) {
        SAP_CRM_EMAIL = sap.getPermissions(Constants.SAP_CRM_EMAIL);

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
