package com.khaadyam.khaadyamusers.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.khaadyam.khaadyamusers.HomeActivity;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.activities.PromotionActivity;
import com.khaadyam.khaadyamusers.activities.RazorpayPaymentActivity;
import com.khaadyam.khaadyamusers.activities.SaveDeliveryLocationActivity;
import com.khaadyam.khaadyamusers.activities.SetDeliveryLocationActivity;
import com.khaadyam.khaadyamusers.adapter.PromotionsAdapter;
import com.khaadyam.khaadyamusers.adapter.ViewCartAdapter;
import com.khaadyam.khaadyamusers.build.api.ApiClient;
import com.khaadyam.khaadyamusers.build.api.ApiInterface;
import com.khaadyam.khaadyamusers.helper.ConnectionHelper;
import com.khaadyam.khaadyamusers.helper.CustomDialog;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.helper.SharedPrefrences;
import com.khaadyam.khaadyamusers.models.AddCart;
import com.khaadyam.khaadyamusers.models.Cart;
import com.khaadyam.khaadyamusers.utils.Utils;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.ViewSkeletonScreen;
import com.robinhood.ticker.TickerUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.khaadyam.khaadyamusers.adapter.ViewCartAdapter.bottomSheetDialogFragment;
import static com.khaadyam.khaadyamusers.utils.Utils.formatPreciseDouble;


/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */
public class CartFragment extends Fragment {

    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.order_item_rv)
    RecyclerView orderItemRv;

    @BindView(R.id.map_marker_image)
    ImageView mapMarkerImage;
    @BindView(R.id.location_error_title)
    TextView locationErrorTitle;
    @BindView(R.id.location_error_sub_title)
    TextView locationErrorSubTitle;
    @BindView(R.id.add_address_btn)
    Button addAddressBtn;
    @BindView(R.id.dummy_image_view)
    ImageView dummyImageView;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.buttonLayout)
    LinearLayout buttonLayout;
    @BindView(R.id.address_header)
    TextView addressHeader;
    @BindView(R.id.address_detail)
    TextView addressDetail;
    @BindView(R.id.address_delivery_time)
    TextView addressDeliveryTime;
    @BindView(R.id.add_address_txt)
    TextView addAddressTxt;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    public static RelativeLayout dataLayout;
    public static RelativeLayout errorLayout;
    @BindView(R.id.location_info_layout)
    LinearLayout locationInfoLayout;
    @BindView(R.id.location_error_layout)
    RelativeLayout locationErrorLayout;
    @BindView(R.id.restaurant_image)
    ImageView restaurantImage;
    @BindView(R.id.restaurant_name)
    TextView restaurantName;
    @BindView(R.id.restaurant_description)
    TextView restaurantDescription;
    @BindView(R.id.tip)
    EditText tip;
    @BindView(R.id.proceed_to_pay_btn)
    Button proceedToPayBtn;
    @BindView(R.id.selected_address_btn)
    Button selectedAddressBtn;
    @BindView(R.id.error_layout_description)
    TextView errorLayoutDescription;
    @BindView(R.id.use_wallet_chk_box)
    CheckBox useWalletChkBox;
    @BindView(R.id.amount_txt)
    TextView amountTxt;
    @BindView(R.id.custom_notes)
    TextView customNotes;
    @BindView(R.id.wallet_layout)
    LinearLayout walletLayout;
    @BindView(R.id.address_layout)
    LinearLayout addressLayout;
    @BindView(R.id.pickup_button)
    CheckBox pickupCheckBox;

    @BindView(R.id.promocode_btn)
    LinearLayout promocCodeBtn;

    @BindView(R.id.discount_amount_promo)
    TextView discountPromo;

    private Context context;
    private ViewGroup toolbar;
    private View toolbarLayout;
    AnimatedVectorDrawableCompat avdProgress;
    //Animation number
    private static final char[] NUMBER_LIST = TickerUtils.getDefaultNumberList();

    public static TextView itemTotalAmount, deliveryCharges, promoCodeApply, discountAmount, serviceTax, payAmount, pickupAddressNote, totalDistance;

    Fragment orderFullViewFragment;
    FragmentManager fragmentManager;
    //Orderitem List
    public static List<Cart> viewCartItemList;

    double priceAmount = 0;
    int discount = 0;
    public static float deliveryChargeValue = 0;
    public static double tax = 0;
    int itemCount = 0;
    int itemQuantity = 0;
    int ADDRESS_SELECTION = 1;

    public int tipPayAmount;

    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    public static ViewCartAdapter viewCartAdapter;
    CustomDialog customDialog;
    ViewSkeletonScreen skeleton;
    ConnectionHelper connectionHelper;
    Activity activity;

    public static HashMap<String, String> checkoutMap;
    private double topPayAmount;
    private String currency;
    private Double shaun;
    private SharedPrefrences sharedPrefrences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        connectionHelper = new ConnectionHelper(context);
        sharedPrefrences = SharedPrefrences.getsharedprefInstance(context);

        /*  Intialize Global Values*/
        itemTotalAmount = (TextView) view.findViewById(R.id.item_total_amount);
        deliveryCharges = (TextView) view.findViewById(R.id.delivery_charges);
        promoCodeApply = (TextView) view.findViewById(R.id.promo_code_apply);
        discountAmount = (TextView) view.findViewById(R.id.discount_amount);
        serviceTax = (TextView) view.findViewById(R.id.service_tax);
        totalDistance = (TextView) view.findViewById(R.id.total_distance);
        payAmount = (TextView) view.findViewById(R.id.total_amount);
        dataLayout = (RelativeLayout) view.findViewById(R.id.data_layout);
        errorLayout = (RelativeLayout) view.findViewById(R.id.error_layout);
        pickupAddressNote = (TextView) view.findViewById(R.id.pickup_address_note);
        promocCodeBtn = view.findViewById(R.id.promocode_btn);
        pickupAddressNote.setVisibility(View.GONE);
        tip = view.findViewById(R.id.tip);

        tip.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    tipPayAmount = 0;
                    setTipToTextView(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    setTipToTextView(s.toString());
                }
            }
        });

        HomeActivity.updateNotificationCount(context, 0);
        customDialog = new CustomDialog(context);

        skeleton = Skeleton.bind(dataLayout)
                .load(R.layout.skeleton_fragment_cart)
                .show();
        viewCartItemList = new ArrayList<>();
        //Offer Restaurant Adapter
        orderItemRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        orderItemRv.setItemAnimator(new DefaultItemAnimator());
        orderItemRv.setHasFixedSize(false);
        orderItemRv.setNestedScrollingEnabled(false);

        //Intialize address Value
        if (GlobalData.getInstance().selectedAddress != null && GlobalData.getInstance().selectedAddress.getLandmark() != null) {
            if (GlobalData.getInstance().addressList.getAddresses().size() == 1)
                addAddressTxt.setText(getString(R.string.add_address));
            else
                addAddressTxt.setText(getString(R.string.change_address));
            addAddressBtn.setBackgroundResource(R.drawable.button_corner_bg_green);
            addAddressBtn.setText(getResources().getString(R.string.proceed_to_pay));
            addressHeader.setText(GlobalData.getInstance().selectedAddress.getType());
            addressDetail.setText(GlobalData.getInstance().selectedAddress.getMapAddress());
            if (viewCartItemList != null && viewCartItemList.size() != 0)
                addressDeliveryTime.setText(viewCartItemList.get(0).getProduct().getShop().getEstimatedDeliveryTime().toString() + " Mins");
        } else if (GlobalData.getInstance().addressList != null) {
            addAddressBtn.setBackgroundResource(R.drawable.button_corner_bg_theme);
            addAddressBtn.setText(getResources().getString(R.string.add_address));
            locationErrorSubTitle.setText(GlobalData.getInstance().addressHeader);
            selectedAddressBtn.setVisibility(View.VISIBLE);
            locationErrorLayout.setVisibility(View.VISIBLE);
            locationInfoLayout.setVisibility(View.GONE);
        } else {
            if (GlobalData.getInstance().selectedAddress != null)
                locationErrorSubTitle.setText(GlobalData.selectedAddress.getMapAddress());
            else
                locationErrorSubTitle.setText(GlobalData.getInstance().addressHeader);
            locationErrorLayout.setVisibility(View.VISIBLE);
            selectedAddressBtn.setVisibility(View.GONE);
            locationInfoLayout.setVisibility(View.GONE);
        }
        return view;
    }

    private void getViewCart() {

        int address_id = 0;
        if (GlobalData.selectedAddress != null) {
            address_id = GlobalData.selectedAddress.getId();
        }

        Call<AddCart> call = apiInterface.getViewCart(address_id);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                skeleton.hide();
                if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                    errorLayout.setVisibility(View.VISIBLE);
                    dataLayout.setVisibility(View.GONE);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, jObjError.optString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
//                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else if (response.isSuccessful()) {
                    customDialog.dismiss();
                    //get Item Count
                    itemCount = response.body().getProductList().size();
                    GlobalData.getInstance().notificationCount = response.body().getProductList().size();
                    if (itemCount == 0) {
                        errorLayout.setVisibility(View.VISIBLE);
                        dataLayout.setVisibility(View.GONE);
                        GlobalData.addCart = response.body();
                        GlobalData.addCart = null;
                    } else {
                        AddCart addCart = response.body();
                        errorLayout.setVisibility(View.GONE);
                        dataLayout.setVisibility(View.VISIBLE);
                        for (int i = 0; i < itemCount; i++) {
                            // Get Total item Quantity
                            itemQuantity = itemQuantity + response.body().getProductList().get(i).getQuantity();
                            //Get product price
                            if (response.body().getProductList().get(i).getProduct().getPrices().getPrice() != null)
                                priceAmount = priceAmount + (response.body().getProductList().get(i).getQuantity() * response.body().getProductList().get(i).getProduct().getPrices().getPrice());

                            if (addCart.getProductList().get(i).getCartAddons() != null && !addCart.getProductList().get(i).getCartAddons().isEmpty()) {
                                for (int j = 0; j < addCart.getProductList().get(i).getCartAddons().size(); j++) {
                                    priceAmount = priceAmount + (addCart.getProductList().get(i).getQuantity() * (addCart.getProductList().get(i).getCartAddons().get(j).getQuantity() *
                                            addCart.getProductList().get(i).getCartAddons().get(j).getAddonProduct().getPrice()));
                                }
                            }
                        }
                        shaun = response.body().getProductList().get(0).getProduct().getShop().getOfferMinAmount();
                        GlobalData.notificationCount = itemQuantity;
                        GlobalData.getInstance().addCartShopId = response.body().getProductList().get(0).getProduct().getShopId();
                        //Set Payment details
                        currency = response.body().getProductList().get(0).getProduct().getPrices().getCurrency();
                        itemTotalAmount.setText(currency + "" + formatPreciseDouble(priceAmount));
                        if (response.body().getProductList().get(0).getProduct().getShop().getOfferMinAmount() != null) {

                            if (response.body().getProductList().get(0).getProduct().getShop().getOfferMinAmount() < priceAmount) {
                                int offerPercentage = response.body().getProductList().get(0).getProduct().getShop().getOfferPercent();
                                discount = (int) (priceAmount * (offerPercentage * 0.01));
                            }

                        }
                        discountAmount.setText("- " + currency + "" + discount);
                        discountPromo.setText(" - "+currency+" "+PromotionsAdapter.promoPrice.toString());
                        topPayAmount = priceAmount - discount - PromotionsAdapter.promoPrice;
                        tax = (response.body().getDeliveryCharges() + topPayAmount) * (response.body().getTaxPercentage() * 0.01);
                        serviceTax.setText(currency + formatPreciseDouble(tax));
                        totalDistance.setText(response.body().getTotalDistance() + " km");
                        topPayAmount = topPayAmount + response.body().getDeliveryCharges() + tax;
                        payAmount.setText(currency + "" + formatPreciseDouble(topPayAmount));
                        sharedPrefrences.settAm(Double.toString(topPayAmount));
                        restaurantName.setText(response.body().getProductList().get(0).getProduct().getShop().getName());
                        restaurantDescription.setText(response.body().getProductList().get(0).getProduct().getShop().getDescription());
                        String image_url = response.body().getProductList().get(0).getProduct().getShop().getAvatar();
                        Glide.with(context).load(image_url).placeholder(R.drawable.ic_restaurant_place_holder).dontAnimate()
                                .error(R.drawable.ic_restaurant_place_holder).into(restaurantImage);
                        deliveryChargeValue = response.body().getDeliveryCharges();
                        deliveryCharges.setText(response.body().getProductList().get(0).getProduct().getPrices().getCurrency() + "" + response.body().getDeliveryCharges());
                        viewCartItemList.clear();
                        viewCartItemList = response.body().getProductList();
                        viewCartAdapter = new ViewCartAdapter(viewCartItemList, context);
                        orderItemRv.setAdapter(viewCartAdapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                errorLayout.setVisibility(View.VISIBLE);
                dataLayout.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.pickup_button)
    public void setPickupCheckBoxClicked() {
        if (pickupCheckBox.isChecked()) {
            double total = topPayAmount - deliveryChargeValue;
            payAmount.setText(currency + "" + formatPreciseDouble(total));
            sharedPrefrences.settAm(Double.toString(total));
            deliveryCharges.setText(currency + "" + 0);
            pickupAddressNote.setVisibility(View.VISIBLE);

//            addressLayout.setVisibility(View.GONE);
//            locationErrorLayout.setVisibility(View.GONE);
//            locationInfoLayout.setVisibility(View.VISIBLE);
        } else {
            payAmount.setText(currency + "" + formatPreciseDouble(topPayAmount));
            deliveryCharges.setText(currency + "" + deliveryChargeValue);
            sharedPrefrences.settAm(Double.toString(topPayAmount));
            pickupAddressNote.setVisibility(View.GONE);


//            if (GlobalData.getInstance().selectedAddress == null) {
//                locationErrorLayout.setVisibility(View.VISIBLE);
//                locationInfoLayout.setVisibility(View.GONE);
//            } else {
//                addressLayout.setVisibility(View.VISIBLE);
//                locationInfoLayout.setVisibility(View.VISIBLE);
//            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        priceAmount = 0;
        discount = 0;
        itemCount = 0;
        itemQuantity = 0;
        if (GlobalData.profileModel != null) {
            int money = GlobalData.profileModel.getWalletBalance();
            dataLayout.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
            skeleton.show();
            errorLayoutDescription.setText(getResources().getString(R.string.cart_error_description));
            if (connectionHelper.isConnectingToInternet()) {
                getViewCart();
            } else {
                Utils.displayMessage(activity, context, getString(R.string.oops_connect_your_internet));
            }
            if (money > 0) {
//                amountTxt.setText(numberFormat.format(money));
                amountTxt.setText(GlobalData.currencySymbol + " " + money);
                walletLayout.setVisibility(View.VISIBLE);
            } else {
                walletLayout.setVisibility(View.INVISIBLE);
            }
        } else {
            dataLayout.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
            errorLayoutDescription.setText(getResources().getString(R.string.please_login_and_order_dishes));
        }
        if (bottomSheetDialogFragment != null)
            bottomSheetDialogFragment.dismiss();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (toolbar != null) {
            toolbar.removeView(toolbarLayout);
        }
    }

    public void FeedbackDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.feedback);
        EditText commentEdit = (EditText) dialog.findViewById(R.id.comment);

        Button submitBtn = (Button) dialog.findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("CartFragment");
        toolbar = (ViewGroup) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
            dummyImageView.setVisibility(View.VISIBLE);
        } else {

            dummyImageView.setVisibility(View.GONE);
        }

    }


    @OnClick({R.id.add_address_txt, R.id.add_address_btn, R.id.selected_address_btn, R.id.proceed_to_pay_btn, R.id.promocode_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_address_txt:
                /**  If address is empty */
                if (addAddressTxt.getText().toString().equalsIgnoreCase(getResources().getString(R.string.change_address))) {
                    startActivityForResult(new Intent(getActivity(), SetDeliveryLocationActivity.class).putExtra("get_address", true), ADDRESS_SELECTION);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                }
                /**  If address is filled */
                else if (addAddressTxt.getText().toString().equalsIgnoreCase(getResources().getString(R.string.add_address))) {
                    startActivityForResult(new Intent(getActivity(), SaveDeliveryLocationActivity.class).putExtra("get_address", true), ADDRESS_SELECTION);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                }
                break;
            case R.id.promocode_btn:

                String itemf = itemTotalAmount.getText().toString();
                String itemg = itemf.replace(currency, "");

//                String f = payAmount.getText().toString();
//                String g = f.replace(currency, "");
                if (shaun > Double.parseDouble(itemg)) {
                    Toast.makeText(context, "PromoCode Available Only Above Minimum Order", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), PromotionActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                }


                break;
            case R.id.add_address_btn:
                /**  If address is empty */
                startActivityForResult(new Intent(getActivity(), SaveDeliveryLocationActivity.class).putExtra("get_address", true), ADDRESS_SELECTION);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                break;
            case R.id.selected_address_btn:
                /**  If address is filled */
                startActivityForResult(new Intent(getActivity(), SetDeliveryLocationActivity.class).putExtra("get_address", true), ADDRESS_SELECTION);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);

                break;

            case R.id.proceed_to_pay_btn:
                /**  If address is filled */
                if (connectionHelper.isConnectingToInternet()) {
//                    checkOut(GlobalData.getInstance().selectedAddress.getId());
                    checkoutMap = new HashMap<>();
                    if (GlobalData.getInstance().selectedAddress != null) {
                        checkoutMap.put("user_address_id", "" + GlobalData.getInstance().selectedAddress.getId());
                        Log.d("testing..", GlobalData.getInstance().selectedAddress.getId() + "");
                    }

                    checkoutMap.put("note", "" + customNotes.getText());

                    if (useWalletChkBox.isChecked())
                        checkoutMap.put("wallet", "1");
                    else
                        checkoutMap.put("wallet", "0");

                    // pickup change
                    boolean walletCheck;

                    if (pickupCheckBox.isChecked()) {
                        checkoutMap.put("pickup", "1");
                        walletCheck = true;
                        topPayAmount = topPayAmount - deliveryChargeValue;
                    } else {
                        walletCheck = false;
                        checkoutMap.put("pickup", "0");
                    }
                    // add changes
                    topPayAmount = topPayAmount + tipPayAmount;
                    checkoutMap.put("tip", tipPayAmount + "");
                    checkoutMap.put("topPayAmount", topPayAmount + "");

                    Log.d("Cart = ", checkoutMap.toString());

                    String itemd = itemTotalAmount.getText().toString();
                    String iteme = itemd.replace(currency, "");

                    String d = payAmount.getText().toString();
                    String e = d.replace(currency, "");
                    if (shaun > Double.parseDouble(iteme)) {
                        Toast.makeText(context, "Min Amount For Delivery is " + shaun.toString(), Toast.LENGTH_SHORT).show();

                    } else {
                        startActivity(new Intent(context, RazorpayPaymentActivity.class).putExtra("topay", e));
//                    startActivity(new Intent(context, AccountPaymentActivity.class).putExtra("is_show_wallet", walletCheck).putExtra("is_show_cash", true));
                        activity.overridePendingTransition(R.anim.anim_nothing, R.anim.slide_out_right);
                    }

                } else {
                    Utils.displayMessage(activity, context, getString(R.string.oops_connect_your_internet));
                }
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.print("CartFragment");
        if (requestCode == ADDRESS_SELECTION && resultCode == Activity.RESULT_OK) {
            System.out.print("CartFragment : Success");
            if (GlobalData.getInstance().selectedAddress != null) {
                locationErrorLayout.setVisibility(View.GONE);
                locationInfoLayout.setVisibility(View.VISIBLE);
                //Intialize address Value
                if (GlobalData.getInstance().selectedAddress != null && GlobalData.getInstance().selectedAddress.getLandmark() != null) {
                    if (GlobalData.getInstance().addressList.getAddresses().size() == 1)
                        addAddressTxt.setText(getString(R.string.add_address));
                    else
                        addAddressTxt.setText(getString(R.string.change_address));
                }
                addressHeader.setText(GlobalData.getInstance().selectedAddress.getType());
                addressDetail.setText(GlobalData.getInstance().selectedAddress.getMapAddress());
                addressDeliveryTime.setText(viewCartItemList.get(0).getProduct().getShop().getEstimatedDeliveryTime().toString() + " Mins");
            } else {
                locationErrorLayout.setVisibility(View.VISIBLE);
                locationInfoLayout.setVisibility(View.GONE);
            }
        } else if (requestCode == ADDRESS_SELECTION && resultCode == Activity.RESULT_CANCELED) {
            System.out.print("CartFragment : Failure");

        }
    }

    @OnClick(R.id.wallet_layout)
    public void onViewClicked() {
    }

    @OnClick(R.id.custom_notes)
    public void onAddCustomNotesClicked() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            final FrameLayout frameView = new FrameLayout(getActivity());
            builder.setView(frameView);

            final AlertDialog alertDialog = builder.create();
            LayoutInflater inflater = alertDialog.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_note_popup, frameView);

            final EditText notes = (EditText) dialogView.findViewById(R.id.notes);
            notes.setText(customNotes.getText());
            Button submit = (Button) dialogView.findViewById(R.id.custom_note_submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customNotes.setText(notes.getText());
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private void setTipToTextView(String tipPayAmount) {

        Log.d("Tip = ", tipPayAmount);

        if (tipPayAmount.equals("")) {
            payAmount.setText(currency + "" + formatPreciseDouble(topPayAmount));
            sharedPrefrences.settAm(Double.toString(topPayAmount));
        } else {
            double total = topPayAmount + Integer.parseInt(tipPayAmount);
            this.tipPayAmount = Integer.parseInt(tipPayAmount);
            payAmount.setText(currency + "" + formatPreciseDouble(total));
            sharedPrefrences.settAm(Double.toString(total));
        }
    }
}