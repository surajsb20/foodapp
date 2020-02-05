package com.khaadyam.khaadyamusers.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.khaadyam.khaadyamusers.build.api.ApiClient;
import com.khaadyam.khaadyamusers.build.api.ApiInterface;
import com.khaadyam.khaadyamusers.chatUser.PushNotifyModel;
import com.khaadyam.khaadyamusers.fragments.CartFragment;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.helper.SharedPrefrences;
import com.khaadyam.khaadyamusers.models.Order;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class RazorpayPaymentActivity extends Activity implements PaymentResultListener {

    //
    public static ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    public static CustomDialog customDialog;
    private Context context;
    SharedPrefrences sharedPrefrences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        context = this;

        //
        Checkout.preload(getApplicationContext());

        //
        startPayment();
    }

    //
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            // sharedPrefrences = SharedPrefrences.getsharedprefInstance(context);
            //double topPayAmount = Double.parseDouble(Objects.requireNonNull(CartFragment.checkoutMap.get("topPayAmount"))) * 100;

            Bundle extras = getIntent().getExtras();
            String newString = extras.getString("topay");

            double topPayAmount = (Double.parseDouble(newString)) * 100;

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
            CartFragment.checkoutMap.put("order_number", razorpayPaymentID);
            CartFragment.checkoutMap.put("payment_mode", "razorpay");
            checkOut(CartFragment.checkoutMap);

        } catch (Exception e) {
            Log.e("RazerpayPayment", "Exception in onPaymentSuccess", e);
        }
    }

    public void sendPushNotification(HashMap<String, String> map) {

        Call<PushNotifyModel> call = apiInterface.newpushnotification(map);
        call.enqueue(new Callback<PushNotifyModel>() {
            @Override
            public void onResponse(@NonNull Call<PushNotifyModel> call, @NonNull Response<PushNotifyModel> response) {
                if (response.body() != null) {
                    Toast.makeText(RazorpayPaymentActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PushNotifyModel> call, @NonNull Throwable t) {
                Toast.makeText(context, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Log.e("RazerpayPayment", "Exception in onPaymentError", e);
        }
    }

    //
    private void checkOut(HashMap<String, String> map) {

        Log.d("Data", map.toString());

        customDialog = new CustomDialog(context);

        customDialog.show();
        Call<Order> call = apiInterface.postCheckout(map);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                customDialog.dismiss();

                Log.d("Pay Response = ", new Gson().toJson(response.body()));
                Log.d("Pay Response Error = ", new Gson().toJson(response.errorBody()));

                if (response.isSuccessful()) {
                    GlobalData.addCart = null;
                    GlobalData.notificationCount = 0;
                    GlobalData.selectedShop = null;
                    GlobalData.profileModel.setWalletBalance(response.body().getUser().getWalletBalance());
                    GlobalData.isSelectedOrder = response.body();
                    startActivity(new Intent(context, CurrentOrderDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    String device_token = GlobalData.profileModel.getDeviceToken();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("device_token", device_token);
                    sendPushNotification(map);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, "Error = " + jObjError.optString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Error = " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {

                customDialog.dismiss();

                Log.d("Pay Error = ", t.getMessage());

                if (t instanceof HttpException) {
                    ResponseBody responseBody = ((HttpException) t).response().errorBody();
                    String errorMessage = getErrorMessage(responseBody);
                }

                Toast.makeText(RazorpayPaymentActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            Log.d("PAY = ", jsonObject.toString());

            return "Something went wrong!";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
