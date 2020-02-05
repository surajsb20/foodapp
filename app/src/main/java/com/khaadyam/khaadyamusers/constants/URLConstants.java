package com.khaadyam.khaadyamusers.constants;

public class URLConstants {

    static String domain = "http://192.168.43.224";//"https://khaadyam.com/kf";

    public static String apiController = "/khaadyam/AppController";//"/AppController";
    public static String base_url = domain + apiController;
    public static String getLunchUser = base_url+"/getLunchUser";
    public static String getMealByShop = base_url+"/getMealByShop";
    public static String getMenuItems = base_url+"/getMenuItems";
    public static String getTSByShop = base_url+"/getTSByShop";
    public static String getPriceByShop = base_url+"/getPriceByShop";
    public static String placeNow = base_url+"/placeNow";
    public static String meal_order = base_url+"/meal_order";
    public static String viewMenu = base_url+"/viewMenu";


    public static Double latitude=0.0;
    public static Double longitude=0.0;
    public static String city="";
    public static String address="";
    public static String addressHeader="";
    public static String accessToken="";
    public static String Rs = "₹";

//    public static String getDeviceId(Context context) {
//        String device_id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//        if (device_id != null)
//            return device_id;
//        else
//            return Build.SERIAL;
//    }
//
//    public static String getImeiNo(Context con) {
//        String str = null;
//        try {
//            String deviceId = ((TelephonyManager) con.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//            if (deviceId != null) {
//                str = deviceId;
//            } else {
//                String android_id = Settings.Secure.getString(con.getContentResolver(),
//                        Settings.Secure.ANDROID_ID);
//                if (android_id != null)
//                    str = android_id;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            str = "2365489563";
//        }
//
//        return str;
//    }
}
