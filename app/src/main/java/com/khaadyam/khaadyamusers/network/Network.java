package com.khaadyam.khaadyamusers.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.khaadyam.khaadyamusers.MealSection.request.PassNullData;
import com.khaadyam.khaadyamusers.MealSection.request.PassThreeData;
import com.khaadyam.khaadyamusers.MealSection.request.PassTwoData;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Network {
    public static Network network;
    Context context;
    SharedPrefrences shPrfs;


    public Network(Context context) {
        this.context = context;

        shPrfs = SharedPrefrences.getsharedprefInstance(this.context);
    }

    public static Network getInstance(Context context) {
        if (network == null)
            network = new Network(context);
        return network;
    }

    public void requestWithJsonObject(String url, PassThreeData threeData, VolleyResponse volley){}
    public void requestWithJsonObject(String url, PassTwoData threeData, VolleyResponse volley){}
    public void requestWithJsonObject(String url, PassNullData threeData, VolleyResponse volley){}


    /*public JSONObject login(String loginId, String latitude, String longitude, String deviceId) {
        JSONObject response ;
        pairs.clear();
        pairs.add(new BasicNameValuePair("LoginID", loginId));
        pairs.add(new BasicNameValuePair("user_latitude", latitude));
        pairs.add(new BasicNameValuePair("user_longitude",longitude));
        pairs.add(new BasicNameValuePair("device_id", deviceId));
        pairs.add(new BasicNameValuePair("device_type", "android"));
        response = ServerConnectivity.post(pairs, URLConstants.login);
        return response;
    }

    public JSONObject updateStatus(String oi,String os) {
        JSONObject response ;
        UserDetail ud=shPrfs.getUserDetail();
        pairs.clear();
        pairs.add(new BasicNameValuePair("LoginID", ud.getLoginID()));
        pairs.add(new BasicNameValuePair("order_status", os));
        pairs.add(new BasicNameValuePair("appkey", ud.getDevice_token()));
        pairs.add(new BasicNameValuePair("order_id", oi));
        response = ServerConnectivity.post(pairs, URLConstants.changeStatus);
        return response;
    }

    public Map<String,String> getCommonMap(){
        Map<String,String> map=new HashMap<>();
        UserDetail lr=shPrfs.getUserDetail();
        map.put("appkey",lr.getDevice_token());
        map.put("loginId",lr.getLoginID());

        return map;
    }
*/






    private byte[] getInByteArray(String path) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/sterlite", "/img1488280203275.png");
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] b = stream.toByteArray();
        Log.w("byte ", b.toString());
        return b;
    }


}
