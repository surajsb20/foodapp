package com.khaadyam.khaadyamusers.MealSection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter1;
import com.khaadyam.khaadyamusers.MealSection.request.PassTwoData;
import com.khaadyam.khaadyamusers.MealSection.response.Meals;
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

public class Meals2 extends AppCompatActivity {

    private Network network;
    private Button subscribe;
    private CardView section3;
    private CardView section7;
    private CardView section15;
    private TextView days3;
    private TextView days7;
    private TextView days15;
    private TextView days28;
    private LinearLayout daySection;
    private CardView section28;
    private String oneday;
    private String sevenday;
    private String fifteenday;
    private String fullmonth;
    public static String sub_id = "x";
    public static String sub_days = "x";
    public static String sub_price = "x";
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals2);
        network = Network.getInstance(getApplicationContext());
        customDialog = new CustomDialog(this);
        TextView title = findViewById(R.id.title);
        title.setText(MealsListAdapter.shopName + "( " + Meal1.type + " )");
        ImageView shopImage = findViewById(R.id.meal_shop_img);
        Glide.with(this).load(MealsListAdapter.shopImage)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(shopImage);
        TextView shopName = findViewById(R.id.restaurant_name);
        shopName.setText(MealsListAdapter.shopName);
        TextView foodType = findViewById(R.id.food_type);
        foodType.setText(Meal1.type);

        subscribe = findViewById(R.id.subscribe_pack);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Meals2.this, Meals4.class));
                overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                finish();
            }
        });


        section3 = findViewById(R.id.s3dayssection);
        section7 = findViewById(R.id.s7dayssection);
        section15 = findViewById(R.id.s15dayssection);
        section28 = findViewById(R.id.s28dayssection);

        days3 = findViewById(R.id.s3days);
        days7 = findViewById(R.id.s7days);
        days15 = findViewById(R.id.s15days);
        days28 = findViewById(R.id.s28days);

        daySection = findViewById(R.id.subscribe_day_section);

        section3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
                sub_id = "1";
                sub_days = "3";
                sub_price = oneday;
                startActivity(new Intent(Meals2.this, Meals4.class));
                overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                finish();
            }
        });
        section7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
                sub_id = "2";
                sub_days = "7";
                sub_price = sevenday;
                startActivity(new Intent(Meals2.this, Meals4.class));
                overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                finish();
            }
        });
        section15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
                sub_id = "3";
                sub_days = "15";
                sub_price = fifteenday;
                startActivity(new Intent(Meals2.this, Meals4.class));
                overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                finish();
            }
        });
        section28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
                sub_id = "4";
                sub_days = "28";
                sub_price = fullmonth;
                startActivity(new Intent(Meals2.this, Meals4.class));
                overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                finish();
            }
        });
        getPriceByShop();
        getUserMeal();
    }

    private void getUserMeal() {
        customDialog.show();
        if (InternetConnection.isConnected(getApplication())) {

            network.requestWithJsonObject(URLConstants.getMealByShop, new PassTwoData(Meal1.meal, MealsListAdapter.shopId),
                    getLunchuserResponse);



        } else {
            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

    VolleyResponse getLunchuserResponse = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {

            try {
                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    customDialog.dismiss();
                    Type token1 = new TypeToken<List<Meals>>() {
                    }.getType();
                    List<Meals> shopLunchList = new Gson().fromJson(obj.getJSONArray("data").toString(), token1);
                    prepareAdapter(shopLunchList);

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

    public void prepareAdapter(List<Meals> responseList) {
        try {
            RecyclerView menuRecycler = findViewById(R.id.recycler_meal);

            if(Meal1.hide_r_v.equalsIgnoreCase("0")){
                menuRecycler.setVisibility(View.VISIBLE);
            }
            else if(Meal1.hide_r_v.equalsIgnoreCase("1")){
                menuRecycler.setVisibility(View.GONE);
            }
            menuRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            MealsListAdapter1 menuItemAdapter = new MealsListAdapter1(responseList, getApplicationContext(), this);
            menuRecycler.setAdapter(menuItemAdapter);
            menuItemAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getPriceByShop() {
//        alertDialog.show();

        if (InternetConnection.isConnected(getApplication())) {
            network.requestWithJsonObject(URLConstants.getPriceByShop, new PassTwoData(MealsListAdapter.shopId,Meal1.typeID),
                    getPricebyShopResponse);

        } else {
            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

    VolleyResponse getPricebyShopResponse = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {

            try {
                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }


                    oneday = obj.getString("oneday");
                    sevenday = obj.getString("sevenday");
                    fifteenday = obj.getString("fifteenday");
                    fullmonth = obj.getString("fullmonth");

                    days3.setText(URLConstants.Rs + " " + oneday + " /meal");
                    days7.setText(URLConstants.Rs + " " + sevenday + " /meal");
                    days15.setText(URLConstants.Rs + " " + fifteenday + " /meal");
                    days28.setText(URLConstants.Rs + " " + fullmonth + " /meal");

                } else {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
//                if (alertDialog.isShowing()) {
//                    alertDialog.dismiss();
//                }
                Toast.makeText(getApplication(), "Check your connection and Try Again !", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
