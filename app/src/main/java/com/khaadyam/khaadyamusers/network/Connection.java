package com.khaadyam.khaadyamusers.network;

import android.content.Context;
import android.location.LocationManager;


public class Connection {

    /*public static   String NetworkConnections(String url, List<NameValuePair> pairs,long auto_id){
        Log.w(url,"par="+pairs.toString());

        String responseEntity =null;
        try{
            HttpParams httpParameters=new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters,10000);
            DefaultHttpClient client=new DefaultHttpClient(httpParameters);
            HttpPost post=new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(pairs));
            HttpResponse response=client.execute(post);
            responseEntity= EntityUtils.toString(response.getEntity());
            Log.w("Gps response",responseEntity);
        }catch(Exception e){e.printStackTrace();}

        return responseEntity;
    }*/

    public static boolean isGpsEnable(Context ctx){
        boolean gps_enabled=false;
        LocationManager lm = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return gps_enabled;
    }


}
