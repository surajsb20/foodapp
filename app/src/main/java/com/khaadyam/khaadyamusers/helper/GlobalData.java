package com.khaadyam.khaadyamusers.helper;

import android.location.Location;

import com.khaadyam.khaadyamusers.models.AddCart;
import com.khaadyam.khaadyamusers.models.Address;
import com.khaadyam.khaadyamusers.models.AddressList;
import com.khaadyam.khaadyamusers.models.Card;
import com.khaadyam.khaadyamusers.models.Cart;
import com.khaadyam.khaadyamusers.models.CartAddon;
import com.khaadyam.khaadyamusers.models.Category;
import com.khaadyam.khaadyamusers.models.Cuisine;
import com.khaadyam.khaadyamusers.models.DisputeMessage;
import com.khaadyam.khaadyamusers.models.Order;
import com.khaadyam.khaadyamusers.models.Product;
import com.khaadyam.khaadyamusers.models.User;
import com.khaadyam.khaadyamusers.models.Otp;
import com.khaadyam.khaadyamusers.models.Shop;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tamil on 9/22/2017.
 */

public class GlobalData {

    public Otp otpModel = null;

    public static double latitude;
    public static double longitude;
    public static String addressHeader = "";
    public static Location CURRENT_LOCATION = null;

    /*------------Filter--------*/
    public static boolean isPureVegApplied = false;
    public static boolean isOfferApplied = false;
    public static boolean shouldContinueService = false;
    public static ArrayList<Integer> cuisineIdArrayList = null;
    public static ArrayList<Card> cardArrayList;
    public static boolean isCardChecked = false;
    public static String loginBy = "manual";
    public static String name, email, access_token, mobileNumber, imageUrl;
    public static String address = "";
    public static int addCartShopId = 0;
    public static User profileModel = null;
    public static Address selectedAddress = null;
    public static Order isSelectedOrder = null;
    public static Product isSelectedProduct = null;
    public static Cart isSelctedCart = null;
    public static List<CartAddon> cartAddons = null;
    public static AddCart addCart = null;
    public static String tvAddress = "";
    public static List<Shop> shopList;
    public static List<Cuisine> cuisineList;
    public static List<Category> categoryList = null;
    public static List<Order> onGoingOrderList;
    public static List<DisputeMessage> disputeMessageList;
    public static List<Order> pastOrderList;
    public static AddressList addressList = null;
    public static List<String> ORDER_STATUS = Arrays.asList("ORDERED", "RECEIVED", "ASSIGNED", "PROCESSING", "REACHED", "PICKEDUP", "ARRIVED", "COMPLETED");

    public static Shop selectedShop;
    public static DisputeMessage isSelectedDispute;

    public static int otpValue = 0;
    public static String mobile = "";
    public static String currencySymbol = "Rs.";
    public static int notificationCount = 0;

    //Search Fragment
    public static List<Shop> searchShopList;
    public static List<Product> searchProductList;

    public static ArrayList<HashMap<String, String>> foodCart;
    public static String accessToken = "";

    private static final GlobalData ourInstance = new GlobalData();

    public static GlobalData getInstance() {
        return ourInstance;
    }

    public static NumberFormat getNumberFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        numberFormat.setCurrency(Currency.getInstance("INR"));
        numberFormat.setMinimumFractionDigits(0);
        return numberFormat;
    }

    private GlobalData() {
    }
}
