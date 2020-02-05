package com.khaadyam.khaadyamusers.fcm;

/**
 * Created by jayakumar on 16/02/17.
 */


import android.util.Log;

import com.khaadyam.khaadyamusers.helper.SharedHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        SharedHelper.putKey(getApplicationContext(),"device_token",""+refreshedToken);
        Log.e(TAG,""+refreshedToken);
    }
}