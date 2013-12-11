package ru.mvideo.omni.sync.subscriptions;

/**
 *
 * @author rassakhatsky
 */
public class Constants {

    /**
     * Subscription type (All)
     */
    public final static String Z_LOY_NEWS_SUBSCR = "Z_LOY_NEWS_SUBSCR"; //Новости М.Видео - Бонус
    public final static String Z_SUBSCR_NEWS = "Z_SUBSCR_NEWS"; //Новости М.Видео
    public final static String Z_SUBSCR_NOVELTY = "Z_SUBSCR_NOVELTY"; //Новинки М.Видео
    public final static String Z_SUBSCR_PROD_DAY = "Z_SUBSCR_PROD_DAY"; //Товар дня
    public final static String Z_SUBSCR_PROMO = "Z_SUBSCR_PROMO"; //Акции М.Видео
    public final static String Z_SURVEY_WILLING = "Z_SURVEY_WILLING";

    public final static String[] SUBSCRIPTIONS = {Z_LOY_NEWS_SUBSCR, Z_SUBSCR_NEWS, Z_SUBSCR_NOVELTY, Z_SUBSCR_PROD_DAY,
        Z_SUBSCR_PROMO};

    /**
     * Subscription frequency (All)
     */
    public final static String ONE_MON = "1MON"; //Once per month
    public final static String TWO_MON = "2MON"; //Twice per month
    public final static String ONE_WEEK = "1WEK";//Once per week
    public final static String ONE_DAY = "1DAY";//Once per day
    public final static String[] FREQUENCY = {ONE_MON, TWO_MON, ONE_WEEK, ONE_DAY};//Once per day

    /**
     * Connection Types (Siebel)
     */
    public final static String SIEBEL_SMS = "SMS";
    public final static String SIEBEL_EMAIL = "EMAIL";
    public final static String SIEBEL_NO = "NO COMMUNICATION";
    public final static String SIEBEL_POST = "POST";
    public final static String SIEBEL_NONE = "None";

    /**
     * SAP CRM Permissions.
     */
    public final static String SAP_CRM_SMS = "SMS";
    public final static String SAP_CRM_EMAIL = "EMAIL";
}
