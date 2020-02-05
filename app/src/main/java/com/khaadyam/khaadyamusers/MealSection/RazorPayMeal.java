package com.khaadyam.khaadyamusers.MealSection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.khaadyam.khaadyamusers.HomeActivity;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.network.InternetConnection;
import com.khaadyam.khaadyamusers.network.Network;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class RazorPayMeal extends Activity implements PaymentResultListener {

    public static CustomDialog customDialog;
    private Context context;
    com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences sharedPrefrences;
    private Network network;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        network = Network.getInstance(getApplicationContext());
        customDialog = new CustomDialog(this);
        sharedPrefrences = com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences.getsharedprefInstance(this);
        context = this;

        //
        Checkout.preload(getApplicationContext());

        //
        startPayment();
    }

    //
    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {

            double topPayAmount = (Double.parseDouble(Meals4.payableForRazor)) * 100;

            JSONObject options = new JSONObject();
            options.put("name", "KhaadhyamFoods");
            options.put("description", "KhaadhyamFoods Order Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", topPayAmount);

            JSONObject preFill = new JSONObject();
            preFill.put("email", GlobalData.profileModel.getEmail());
            preFill.put("contact", GlobalData.profileModel.getPhone());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            placeNow(razorpayPaymentID);
            finish();
        } catch (Exception e) {
            Log.e("RazerpayPayment", "Exception in onPaymentSuccess", e);
        }
    }


    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } catch (Exception e) {
            Log.e("RazerpayPayment", "Exception in onPaymentError", e);
        }
    }

    private void placeNow(String payment_id) {
        if (InternetConnection.isConnected(getApplication())) {
            customDialog.show();
            /*
            network.requestWithJsonObject(URLConstants.placeNow, new PassPlaceNow(GlobalData.profileModel.getId().toString(), MealsListAdapter.shopId,
                            Meals2.sub_days, Meals4.oldDateForRazor, Meals4.newDateForRazor, TimeSlotAdapter.ts_id, Meals4.payableForRazor, Meals4.adddressForRazor, payment_id),
                    getPlaceNowResponse);*/
        } else {
            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    VolleyResponse getPlaceNowResponse = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {

            try {
                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    customDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), MealOrderActivity.class));
                    overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
                    finish();

                } else {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    customDialog.dismiss();
                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
//                if (alertDialog.isShowing()) {
//                    alertDialog.dismiss();
//                }
                customDialog.dismiss();
                Toast.makeText(getApplication(), "Check your connection and Try Again !", Toast.LENGTH_SHORT).show();
            }
        }
    };


}
