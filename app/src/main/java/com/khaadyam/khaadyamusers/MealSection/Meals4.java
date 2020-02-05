package com.khaadyam.khaadyamusers.MealSection;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.HomeActivity;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter;
import com.khaadyam.khaadyamusers.MealSection.adapter.TimeSlotAdapter;
import com.khaadyam.khaadyamusers.MealSection.request.PassNullData;
import com.khaadyam.khaadyamusers.MealSection.request.PassTwoData;
import com.khaadyam.khaadyamusers.MealSection.response.TimeSlotResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.constants.URLConstants;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.network.InternetConnection;
import com.khaadyam.khaadyamusers.network.Network;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Meals4 extends AppCompatActivity {

    private TextView days3;
    private TextView days7;
    private TextView days15;
    private TextView days28;
    private LinearLayout daySection;
    public LinearLayout slot;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView dateTextView;
    private String newDate;
    private String oldDate;
    private Network network;
    private LinearLayout dateSection;
    private String oneday;
    private String sevenday;
    private String fifteenday;
    private String fullmonth;
    private CardView billSection;
    private TextView itemtotal;
    private String totalPayable;
    private CardView section3;
    private CardView section7;
    private CardView section15;
    private CardView section28;
    private TextView h_total;
    private TextView h_days;
    private TextView h_rate;
    private CustomDialog customDialog;
    private EditText address;
    private EditText address1;
    private EditText address2;
    private EditText address3;
    private EditText address4;
    private EditText address5;
    private String addressComplete;
    int ADDRESS_SELECTION = 1;
    com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences sharedPrefrences;
    public static String payableForRazor;
    public static String oldDateForRazor;
    public static String newDateForRazor;
    public static String adddressForRazor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals4);
        network = Network.getInstance(getApplicationContext());
        customDialog = new CustomDialog(this);

        sharedPrefrences = SharedPrefrences.getsharedprefInstance(this);

        TextView restaurantName = findViewById(R.id.restaurant_name);
        restaurantName.setText(MealsListAdapter.shopName);
        updateLabelOld();
        section3 = findViewById(R.id.s3dayssection);
        section7 = findViewById(R.id.s7dayssection);
        section15 = findViewById(R.id.s15dayssection);
        section28 = findViewById(R.id.s28dayssection);
//        address = findViewById(R.id.address);
        days3 = findViewById(R.id.s3days);
        days7 = findViewById(R.id.s7days);
        days15 = findViewById(R.id.s15days);
        days28 = findViewById(R.id.s28days);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        address3 = findViewById(R.id.address3);
        address4 = findViewById(R.id.address4);
        address5 = findViewById(R.id.address5);
        daySection = findViewById(R.id.subscribe_day_section);
        slot = findViewById(R.id.subscribe_slot_section);

        final TextView tvAddress = findViewById(R.id.tv_address);

        if (sharedPrefrences.getTvAddress().equalsIgnoreCase("")) {
            tvAddress.setText("CLICK HERE TO ADD/CHANGE ADDRESS");
        } else {
            tvAddress.setText(sharedPrefrences.getTvAddress());
        }

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalData.profileModel != null) {
                    startActivity(new Intent(getApplication(), AddressActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                } else {
                    Toast.makeText(getApplicationContext(), "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        billSection = findViewById(R.id.bill_section);

        ImageView meal_shop_img = findViewById(R.id.meal_shop_img);
        Glide.with(this).load(MealsListAdapter.shopImage)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(meal_shop_img);
        TextView datePickerSection = findViewById(R.id.date_picker_section);

        final Button placeNow = findViewById(R.id.place_now);
        placeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adddressForRazor = tvAddress.getText().toString();
                if(adddressForRazor.equalsIgnoreCase("CLICK HERE TO ADD/CHANGE ADDRESS")){
                    Toast.makeText(Meals4.this, "Check Address", Toast.LENGTH_SHORT).show();
                }else {
                    placeNow();
                }
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datePickerSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Meals4.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar
                        .get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        h_days = findViewById(R.id.days_applied);
        h_rate = findViewById(R.id.one_meal_rate);
        h_total = findViewById(R.id.total_amount_head);
        h_days.setText(Meals2.sub_days + " days");
        h_rate.setText(URLConstants.Rs + " " + Meals2.sub_price + " /meal");
        String tpayable = String.valueOf(Integer.parseInt(Meals2.sub_price) * Integer.parseInt(Meals2.sub_days));
        h_total.setText(URLConstants.Rs + " " + tpayable);
        getTimeSlot();
//        getPriceByShop();
//        days3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "1";
//                sub_days = "3";
//                sub_price = oneday;
//            }
//        });
//        days7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "2";
//                sub_days = "7";
//                sub_price = sevenday;
//            }
//        });
//        days15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "3";
//                sub_days = "15";
//                sub_price = fifteenday;
//            }
//        });
//        days28.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "4";
//                sub_days = "28";
//                sub_price = fullmonth;
//            }
//        });
//        section3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "1";
//                sub_days = "3";
//                sub_price = oneday;
//            }
//        });
//        section7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "2";
//                sub_days = "7";
//                sub_price = sevenday;
//            }
//        });
//        section15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "3";
//                sub_days = "15";
//                sub_price = fifteenday;
//            }
//        });
//        section28.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                daySection.setVisibility(View.GONE);
//                getTimeSlot();
//                sub_id = "4";
//                sub_days = "28";
//                sub_price = fullmonth;
//
//            }
//        });

    }

    public void showDateSection() {
//        slot.setVisibility(View.GONE);
        dateSection = findViewById(R.id.choose_date_section);
        dateSection.setVisibility(View.VISIBLE);
    }


    private void updateLabelOld() {

        Date c = Calendar.getInstance().getTime();


        String myFormat = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateTextView = findViewById(R.id.text_date_picker);


        oldDate = sdf.format(c);
//        System.out.println("Date before Addition: "+oldDate);
        //Specifying date format that matches the given date
        SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            //Setting the date to the given date
            cal.setTime(sdfnew.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(Meals2.sub_days) - 1);
        //Date after adding the days to the given date
        newDate = sdfnew.format(cal.getTime());
        //Displaying the new Date after addition of Days
//        System.out.println("Date after Addition: "+newDate);

        dateTextView.setText(oldDate + " to " + newDate);

//        billSection.setVisibility(View.VISIBLE);
//
        itemtotal = findViewById(R.id.item_total);
        totalPayable = String.valueOf(Integer.parseInt(Meals2.sub_price) * Integer.parseInt(Meals2.sub_days));
        itemtotal.setText(URLConstants.Rs + totalPayable);
        TextView amountPaYable = findViewById(R.id.amount_payable);
        amountPaYable.setText(URLConstants.Rs + totalPayable);

    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateTextView = findViewById(R.id.text_date_picker);


        oldDate = sdf.format(myCalendar.getTime());
//        System.out.println("Date before Addition: "+oldDate);
        //Specifying date format that matches the given date
        SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            //Setting the date to the given date
            cal.setTime(sdfnew.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(Meals2.sub_days) - 1);
        //Date after adding the days to the given date
        newDate = sdfnew.format(cal.getTime());
        //Displaying the new Date after addition of Days
//        System.out.println("Date after Addition: "+newDate);

        dateTextView.setText(oldDate + " to " + newDate);

        billSection.setVisibility(View.VISIBLE);

        itemtotal = findViewById(R.id.item_total);
        totalPayable = String.valueOf(Integer.parseInt(Meals2.sub_price) * Integer.parseInt(Meals2.sub_days));
        itemtotal.setText(URLConstants.Rs + totalPayable);
        TextView amountPaYable = findViewById(R.id.amount_payable);
        amountPaYable.setText(URLConstants.Rs + totalPayable);

    }

    private void getTimeSlot() {
//        alertDialog.show();
        String type = "x";
        if (Meal1.type.equalsIgnoreCase("LUNCH")) {
            type = "1";
        }
        if (Meal1.type.equalsIgnoreCase("DINNER")) {
            type = "2";
        }
        slot.setVisibility(View.VISIBLE);
        if (InternetConnection.isConnected(getApplication())) {
            network.requestWithJsonObject(URLConstants.getTSByShop, new PassTwoData(MealsListAdapter.shopId, type),
                    getMenuResponse);
        } else {
            Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void placeNow() {
        if (InternetConnection.isConnected(getApplication())) {
            customDialog.show();

            payableForRazor = totalPayable;
            oldDateForRazor = oldDate;
            newDateForRazor = newDate;
            adddressForRazor = sharedPrefrences.getTvAddress();

            startActivity(new Intent(this, RazorPayMeal.class));

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
                    startActivity(new Intent(Meals4.this, HomeActivity.class));
                    overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();


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

    VolleyResponse getMenuResponse = new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {

            try {
                if (obj.has("error_code") && obj.getString("error_code").equals("0")) {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    Type token1 = new TypeToken<List<TimeSlotResponse>>() {
                    }.getType();
                    List<TimeSlotResponse> list = new Gson().fromJson(obj.getJSONArray("data").toString(), token1);
                    prepareAdapter(list);

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

    public void prepareAdapter(List<TimeSlotResponse> list) {
        try {
            RecyclerView menuRecycler = findViewById(R.id.ts_recycler);

            menuRecycler.setLayoutManager(new GridLayoutManager(this, 2));

//            menuRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            TimeSlotAdapter menuItemAdapter = new TimeSlotAdapter(list, getApplicationContext(), this);
            menuRecycler.setAdapter(menuItemAdapter);
            menuItemAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getPriceByShop() {
//        alertDialog.show();

        if (InternetConnection.isConnected(getApplication())) {
            network.requestWithJsonObject(URLConstants.getPriceByShop, new PassNullData(MealsListAdapter.shopId),
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

                    days3.setText(URLConstants.Rs + " " + oneday + " meal/day");
                    days7.setText(URLConstants.Rs + " " + sevenday + " meal/day");
                    days15.setText(URLConstants.Rs + " " + fifteenday + " meal/day");
                    days28.setText(URLConstants.Rs + " " + fullmonth + " meal/day");

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
