package com.khaadyam.khaadyamusers.MealSection;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.khaadyam.khaadyamusers.MealSection.adapter.MealsListAdapter;
import com.khaadyam.khaadyamusers.MealSection.request.PassThreeData;
import com.khaadyam.khaadyamusers.MealSection.response.ShopLunch;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.adapter.SliderAdapter;
import com.khaadyam.khaadyamusers.build.api.ApiClient;
import com.khaadyam.khaadyamusers.build.api.ApiInterface;
import com.khaadyam.khaadyamusers.chatUser.SliderModel;
import com.khaadyam.khaadyamusers.chatUser.SliderResponse;
import com.khaadyam.khaadyamusers.constants.URLConstants;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.interfacehelper.VolleyResponse;
import com.khaadyam.khaadyamusers.network.InternetConnection;
import com.khaadyam.khaadyamusers.network.Network;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Meal1 extends AppCompatActivity  implements LocationListener {

    public static final String inputFormat = "HH:mm";
    private SharedPrefrences sharedPrefrences;
    private SpotsDialog alertDialog;
    Network network;
    private Fragment fragment;
    FragmentTransaction transaction;
    private static int currentPage = 0;
    private int num_pages;
    private FragmentManager fragmentManager;
    public static String meal = "1";
    public static String type = "LUNCH";
    public static String typeID = "1";
    private AHBottomNavigation bottomNavigation;
    private ViewPager mViewPager;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    private RecyclerView menuRecycler;
    public static String hide_r_v = "0";

    CustomDialog customDialog;


    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;

    String latitude="";
    String longitude="";
    private LinearLayout frontSection;
    private LinearLayout emptySection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal1);

        network = Network.getInstance(getApplicationContext());
        sharedPrefrences = SharedPrefrences.getsharedprefInstance(getApplication());
//        alertDialog = new SpotsDialog(getApplication());
        customDialog = new CustomDialog(this);

        Call<SliderResponse> sliderResponseCall = apiInterface.getSlider();
        sliderResponseCall.enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(@NonNull Call<SliderResponse> sliderResponseCall, @NonNull Response<SliderResponse> response) {
                if (response.body() != null) {
                    List<SliderModel> sliderModelList = response.body().getSlider();
                    try {

                        prepareSliderAdapter(sliderModelList);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SliderResponse> sliderResponseCall1, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
        if (!isGPS && !isNetwork) {
            Log.d(TAG, "Connection off");
            showSettingsAlert();
            getLastLocation();
        } else {
            Log.d(TAG, "Connection on");
            // check permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    Log.d(TAG, "Permission requests");
                    canGetLocation = false;
                }
            }

            // get location
            getLocation();
        }
        frontSection = findViewById(R.id.front_view_section);
        emptySection = findViewById(R.id.empty_section);
//        fragmentManager = getSupportFragmentManager();
//        transaction = fragmentManager.beginTransaction();

//
//        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
//
//        // Create items
//        AHBottomNavigationItem item0 = new AHBottomNavigationItem("Plan", R.drawable.ic_date_range, R.color.grey);
//        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_cereal, R.color.grey);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.search, R.drawable.ic_search, R.color.grey);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Cart", R.drawable.ic_delivery_packages_on_a_trolley, R.color.grey);
//        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Profile", R.drawable.ic_user, R.color.grey);
//        // Add items
//
//        bottomNavigation.addItem(item0);
//        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);
//        bottomNavigation.addItem(item4);
//
//        // Set background color
//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
//
//        // Disable the translation inside the CoordinatorLayout
//        bottomNavigation.setBehaviorTranslationEnabled(true);
//        bottomNavigation.setTranslucentNavigationEnabled(true);
//
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//
//// Change colors
//        bottomNavigation.setAccentColor(Color.parseColor("#b31101"));
//        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
//
//        // Set listeners
//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public boolean onTabSelected(int position, boolean wasSelected) {
//                // Do something cool here...
//                switch (position) {
//                    case 0:
//                        startActivity(new Intent(Meal1.this, Meal1.class));
//                        overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
//                        break;
//                    case 1:
//                        startActivity(new Intent(Meal1.this, HomeActivity.class));
//                        overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
//                        break;
//                    case 2:
//                        startActivity(new Intent(Meal1.this, HomeActivity.class));
//                        overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
//                        break;
//                    case 3:
//                        startActivity(new Intent(Meal1.this, HomeActivity.class));
//                        overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
//                        break;
//                    case 4:
//                        startActivity(new Intent(Meal1.this, HomeActivity.class));
//                        overridePendingTransition(R.anim.anim_push_left_in,R.anim.anim_nothing);
//                        break;
//                }
//
//                transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.main_container, fragment).commit();
//                return true;
//            }
//        });

//        Toast.makeText(this, sharedPrefrences.getRegId(), Toast.LENGTH_SHORT).show();
        getUserMeal();
        LinearLayout lunch = findViewById(R.id.lunch_section);
        LinearLayout dinner = findViewById(R.id.dinner_section);

        final View luchView = findViewById(R.id.lunch_view);
        final View dinnerView = findViewById(R.id.dinner_view);

        luchView.setVisibility(View.VISIBLE);

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal = "1";
                type = "LUNCH";
                typeID = "1";
                getUserMeal();
                luchView.setVisibility(View.VISIBLE);
                dinnerView.setVisibility(View.GONE);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal = "2";
                type = "DINNER";
                typeID = "2";
                getUserMeal();
                luchView.setVisibility(View.GONE);
                dinnerView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getUserMeal() {
        customDialog.show();

        if (InternetConnection.isConnected(getApplication())) {
            network.requestWithJsonObject(URLConstants.getLunchUser, new PassThreeData(meal,latitude,longitude),
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
                    customDialog.dismiss();
                    Type token1 = new TypeToken<List<ShopLunch>>() {
                    }.getType();
                    List<ShopLunch> shopLunchList = new Gson().fromJson(obj.getJSONArray("data").toString(), token1);
                    prepareAdapter(shopLunchList);

                } else {
//                    if (alertDialog.isShowing()) {
//                        alertDialog.hide();
//                    }
                    customDialog.dismiss();
                    frontSection.setVisibility(View.GONE);
                    emptySection.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
                    menuRecycler.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
//                if (alertDialog.isShowing()) {
//                    alertDialog.dismiss();
//                }
                frontSection.setVisibility(View.GONE);
                emptySection.setVisibility(View.VISIBLE);
                customDialog.dismiss();
                Toast.makeText(getApplication(), obj.getString("response_string"), Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void prepareAdapter(List<ShopLunch> responseList) {
        try {
            menuRecycler = findViewById(R.id.recycler_meal);

            menuRecycler.setVisibility(View.VISIBLE);

            menuRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            MealsListAdapter menuItemAdapter = new MealsListAdapter(responseList, getApplicationContext(), this);
            menuRecycler.setAdapter(menuItemAdapter);
            menuItemAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void prepareSliderAdapter(List<SliderModel> sliderResponses) {
        try {

            SliderAdapter adapterView = new SliderAdapter(getApplicationContext(), sliderResponses);
            mViewPager = findViewById(R.id.home_slider);
            mViewPager.setAdapter(adapterView);


            CirclePageIndicator indicator = findViewById(R.id.indicator);
            indicator.setViewPager(mViewPager);

            final float density = getResources().getDisplayMetrics().density;
            indicator.setRadius(3 * density);

            num_pages = sliderResponses.size();

            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == num_pages) {
                        currentPage = 0;
                    }
                    mViewPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int pos) {
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        updateUI(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        try {
            if (canGetLocation) {
                Log.d(TAG, "Can get location");
                if (isGPS) {
                    // from GPS
                    Log.d(TAG, "GPS on");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) {
                    // from Network Provider
                    Log.d(TAG, "NETWORK_PROVIDER on");
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            } else {
                Log.d(TAG, "Can't get location");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            Log.d(TAG, provider);
            Log.d(TAG, location == null ? "NO LastLocation" : location.toString());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                Log.d(TAG, "onRequestPermissionsResult");
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(permissionsRejected.toArray(
                                                new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                    }
                                }
                            });
                            return;
                        }
                    }
                } else {
                    Log.d(TAG, "No rejected permissions.");
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void updateUI(Location loc) {
        Log.d(TAG, "updateUI");
        latitude=Double.toString(loc.getLatitude());
        longitude=Double.toString(loc.getLongitude());
//        tvLatitude.setText(Double.toString(loc.getLatitude()));
//        tvLongitude.setText(Double.toString(loc.getLongitude()));
//        tvTime.setText(DateFormat.getTimeInstance().format(loc.getTime()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


}