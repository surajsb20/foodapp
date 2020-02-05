package com.khaadyam.khaadyamusers.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefrences {

    private static SharedPrefrences gardenSharedPrfs;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String sharedprfstag="cairn_prfs";
    private static final String prf_login_status="loginstatus";
    private static final String prf_loginid="loginid";
    private static final String prf_imei_no="imei";
    private static final String prf_user_detail="userdetail";
    private static final String offline_timestamp="2016-09-22 07:00:00";
    private static Context mCtx;


    private SharedPrefrences(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(sharedprfstag, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        mCtx = context;
    }

    public static SharedPrefrences getsharedprefInstance(Context con) {
        if (gardenSharedPrfs == null)
            gardenSharedPrfs = new SharedPrefrences(con);
        return gardenSharedPrfs;
    }

    public SharedPreferences getAppSharedPrefs() {
        return appSharedPrefs;
    }

    public void setAppSharedPrefs(SharedPreferences appSharedPrefs) {
        this.appSharedPrefs = appSharedPrefs;
    }

    public SharedPreferences.Editor getPrefsEditor() {
        return prefsEditor;
    }

    public void Commit() {
        prefsEditor.commit();
    }


    public void settAm(String count)
    {

        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString("tAm",count);
        prefsEditor.commit();
    }
    public String getTAm()
    {
        String cartitem_count=appSharedPrefs.getString("tAm","0");
        return cartitem_count;
    }

}
