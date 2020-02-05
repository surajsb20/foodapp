package com.khaadyam.khaadyamusers.MealSection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter1;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter3;
import com.khaadyam.khaadyamusers.MealSection.request.PassNullData;
import com.khaadyam.khaadyamusers.MealSection.response.MenuResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.constants.URLConstants;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.network.InternetConnection;
import com.khaadyam.khaadyamusers.network.Network;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class Meals3 extends AppCompatActivity {
    Network network;
//    private Date date;
//    private Date dateCompareOne;
//    private Date dateCompareTwo;
//
//    private String compareStringOne = "4:00";
//    private String compareStringTwo = "7:00";
    private Button orderNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals3);

        network = Network.getInstance(getApplicationContext());

        TextView restaurantName = findViewById(R.id.restaurant_name);
        restaurantName.setText(MealsListAdapter1.mealName.toUpperCase());
        orderNow = findViewById(R.id.order_now);
        ImageView meal_shop_img=findViewById(R.id.meal_shop_img);
        Glide.with(this).load(MealsListAdapter1.meal_image)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(meal_shop_img);
        Button orderNow=findViewById(R.id.order_now);
        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Meals3.this,Meals2.class));
                overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
                Meal1.hide_r_v = "1";
                finish();
            }
        });
        getMenu();

    }

    private void getMenu() {
//        alertDialog.show();

        if (InternetConnection.isConnected(getApplication())) {
            network.requestWithJsonObject(URLConstants.getMenuItems, new PassNullData(MealsListAdapter1.mealId),
                    getMenuResponse);
        } else {
            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

    VolleyResponse getMenuResponse = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {

            try {
                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    Type token1 = new TypeToken<List<MenuResponse>>() {
                    }.getType();
                    List<MenuResponse> shopLunchList = new Gson().fromJson(obj.getJSONArray("data").toString(), token1);
                    prepareAdapter(shopLunchList);

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

    public void prepareAdapter(List<MenuResponse> responseList) {
        try {
            RecyclerView menuRecycler = findViewById(R.id.recycler_menu);

            menuRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            MealsListAdapter3 menuItemAdapter = new MealsListAdapter3(responseList, getApplicationContext(), this);
            menuRecycler.setAdapter(menuItemAdapter);
            menuItemAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//
//    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
//
//    private void compareForLunch() {
//        Calendar now = Calendar.getInstance();
//
//        int hour = now.get(Calendar.HOUR);
//        int minute = now.get(Calendar.MINUTE);
//
//        date = parseDate(hour + ":" + minute);
//        dateCompareOne = parseDate(compareStringOne);
//        dateCompareTwo = parseDate(compareStringTwo);
//
//        if (date.before(dateCompareTwo)) {
//            orderNow.setVisibility(View.VISIBLE);
//        }
//        if (date.after(dateCompareOne)) {
//            Toast.makeText(this, "Dinner", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private Date parseDate(String date) {
//
//        try {
//            return inputParser.parse(date);
//        } catch (java.text.ParseException e) {
//            return new Date(0);
//        }
//    }


//
//    private void getPriceByShop() {
////        alertDialog.show();
//
//        if (InternetConnection.isConnected(getApplication())) {
//            network.requestWithJsonObject(URLConstants.getPriceByShop, new PassNullData(MealsListAdapter.shopId),
//                    getPricebyShopResponse);
//
//        } else {
//            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//
//    VolleyResponse getPricebyShopResponse = new VolleyResponse() {
//        @Override
//        public void onResponse(JSONObject obj) throws Exception {
//
//            try {
//                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
////                    if (alertDialog.isShowing()) {
////                        alertDialog.hide();
////                    }
//
//
//                    String oneday = obj.getString("oneday");
//                    orderNow.setText("ORDER NOW for "+URLConstants.Rs+" "+oneday);
//
//                } else {
////                    if (alertDialog.isShowing()) {
////                        alertDialog.hide();
////                    }
//                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
////                if (alertDialog.isShowing()) {
////                    alertDialog.dismiss();
////                }
//                Toast.makeText(getApplication(), "Check your connection and Try Again !", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };
}
