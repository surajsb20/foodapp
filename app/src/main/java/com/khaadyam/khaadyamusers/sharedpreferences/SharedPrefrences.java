package com.khaadyam.khaadyamusers.sharedpreferences;

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

//    public void setBaseURL(String url) {
//        this.prefsEditor = appSharedPrefs.edit();
//        prefsEditor.putString("base_url", url);
//        prefsEditor.commit();
//    }
//
//    public String getBaseURL() {
//        return appSharedPrefs.getString("base_url", "http://pmms.logimetrix.me/api");
//    }

    public void setLoggedIn(boolean status){
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putBoolean("isLoggedIn",status);
        prefsEditor.commit();
    }

    public boolean isLoggedIn(){

        return appSharedPrefs.getBoolean("isLoggedIn",false);
    }


    public void setPagePref(boolean status){
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putBoolean("isPagePref",status);
        prefsEditor.commit();
    }

    public boolean isPagePref(){

        return appSharedPrefs.getBoolean("isPagePref",false);
    }

    public String getRegMobile() {
        return appSharedPrefs.getString("regMobile", "");
    }

    public void setRegMobile(String regMobile) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("regMobile", regMobile);
        prefsEditor.commit();
    }


    public String getTvAddress() {
        return appSharedPrefs.getString("address", "");
    }

    public void setTvAddress(String address) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("address", address);
        prefsEditor.commit();
    }

    public String getRegName() {
        return appSharedPrefs.getString("regName", "");
    }

    public void setRegName(String regName) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("regName", regName);
        prefsEditor.commit();
    }

    public String getRegId() {
        return appSharedPrefs.getString("regId", "");
    }

    public void setRegId(String regId) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("regId", regId);
        prefsEditor.commit();
    }

    public String getMealId() {
        return appSharedPrefs.getString("mealId", "");
    }

    public void setMealId(String mealId) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("mealId", mealId);
        prefsEditor.commit();
    }

    public String getPage() {
        return appSharedPrefs.getString("page", "");
    }

    public void setPage(String page) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("page", page);
        prefsEditor.commit();
    }


    public String getRegEmail() {
        return appSharedPrefs.getString("regEmail", "");
    }

    public void setRegEmail(String regEmail) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("regEmail", regEmail);
        prefsEditor.commit();
    }

    public String getSoftwareType() {
        return appSharedPrefs.getString("softwareType", "");
    }

    public void setSoftwareType(String softwareType) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("softwareType", softwareType);
        prefsEditor.commit();
    }

    public String getLicKey() {
        return appSharedPrefs.getString("licKey", "");
    }

    public void setLicKey(String licKey) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("licKey", licKey);
        prefsEditor.commit();
    }

    public String getRestaurantImage() {
        return appSharedPrefs.getString("restaurantImage", "");
    }

    public void setRestaurantImage(String restaurantImage) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("restaurantImage", restaurantImage);
        prefsEditor.commit();
    }

    public String getLicNo() {
        return appSharedPrefs.getString("licNo", "");
    }

    public void setLicNo(String licNo) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("licNo", licNo);
        prefsEditor.commit();
    }

    public void setLocality(String status){
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString("currentlocation",status);
        prefsEditor.commit();
    }

    public String getLocality() {
        return appSharedPrefs.getString("currentlocation", "");
    }
    public void setFormattedAddress(String status){
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString("formattedAddress",status);
        prefsEditor.commit();
    }

    public String getFormattedAddress() {
        return appSharedPrefs.getString("formattedAddress", "");
    }



//
//    public void logout() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(sharedprfstag, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
//    }
}
