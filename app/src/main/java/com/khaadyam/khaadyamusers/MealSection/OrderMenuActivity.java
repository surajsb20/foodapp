package com.khaadyam.khaadyamusers.MealSection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.khaadyam.khaadyamusers.MealSection.adapter.MealOrderAdapter;
import com.khaadyam.khaadyamusers.MealSection.adapter.OrderMenuAdapter;
import com.khaadyam.khaadyamusers.MealSection.request.PassThreeData;
import com.khaadyam.khaadyamusers.MealSection.response.OrderMenuResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.constants.URLConstants;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.network.InternetConnection;
import com.khaadyam.khaadyamusers.network.Network;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class OrderMenuActivity extends AppCompatActivity {

    private Network network;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        network = Network.getInstance(getApplicationContext());
        customDialog = new CustomDialog(this);
        requestOrder();

    }


    private void requestOrder() {
        if (InternetConnection.isConnected(this)) {
            customDialog.show();

            network.requestWithJsonObject(URLConstants.viewMenu, new PassThreeData(MealOrderAdapter.meal_shop_id,MealOrderAdapter.start_date,MealOrderAdapter.end_date
            ), response);

        } else {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    VolleyResponse response = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {
            if (obj.getString("error_code").equals("0")) {
                if (obj.has("data") && !obj.isNull("data")) {

                    if (customDialog.isShowing()) {
                        customDialog.dismiss();
                    }
                    Type token3 = new TypeToken<List<OrderMenuResponse>>() {
                    }.getType();
                    List<OrderMenuResponse> list = new Gson().fromJson(obj.getJSONArray("data").toString(), token3);
                    prepareRecyclerView(list);
                } else
                    customDialog.dismiss();
            } else {
                customDialog.dismiss();
                Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
            }
        }
    };


    private void prepareRecyclerView(List<OrderMenuResponse> list) {
        try {
            RecyclerView recycler = findViewById(R.id.recycler);

            recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
            OrderMenuAdapter mAdapter_jobs = new OrderMenuAdapter(list, getApplicationContext(),this);
            recycler.setAdapter(mAdapter_jobs);
            mAdapter_jobs.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MealOrderActivity.class));
        finish();
    }
}
