package com.khaadyam.khaadyamusers.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.activities.HotelViewActivity;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.models.Shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {
    private List<Shop> list;
    private Context context;
    private Activity activity;

    public RestaurantsAdapter(List<Shop> list, Context con, Activity act) {
        this.list = list;
        this.context = con;
        this.activity = act;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(Shop item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Shop item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Shop shops = list.get(position);
        Glide.with(context).load(shops.getAvatar())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(holder.dishImg);
        holder.restaurantName.setText(shops.getName());
        holder.category.setText(shops.getDescription());


        if (shops.getOfferPercent() == null) {
            holder.offer.setVisibility(View.GONE);
        } else {
            holder.offer.setVisibility(View.VISIBLE);
            holder.offer.setText("Flat " + shops.getOfferPercent().toString() + "% offer on all Orders");
        }
//        if(shops.getav().equalsIgnoreCase("")){
//            holder.offer.setVisibility(View.GONE);
//            holder.restaurantInfo.setVisibility(View.GONE);
//
//        }else {
//            holder.restaurantInfo.setVisibility(View.VISIBLE);
//            holder.restaurantInfo.setText(shops.getAvailability());
//        }

        if (shops.getRatings() != null) {
            Double rating = new BigDecimal(shops.getRatings().getRating()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            holder.rating.setText("" + rating);
        } else
            holder.rating.setText("No Rating");
        holder.distanceTime.setText(shops.getEstimatedDeliveryTime().toString() + " Mins");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout itemView;
        private ImageView dishImg;
        private TextView restaurantName, category, offer, rating, restaurantInfo, price, distanceTime;


        private MyViewHolder(View view) {
            super(view);
            itemView = (LinearLayout) view.findViewById(R.id.item_view);
            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            category = (TextView) view.findViewById(R.id.category);
            offer = (TextView) view.findViewById(R.id.offer);
            rating = (TextView) view.findViewById(R.id.rating);
            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
            distanceTime = (TextView) view.findViewById(R.id.distance_time);
            price = (TextView) view.findViewById(R.id.price);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            if (v.getId() == itemView.getId()) {

                Calendar calendar4 = Calendar.getInstance();
                SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
                String currentTime = formatter1.format(calendar4.getTime());
//                String endtime = list.get(getAdapterPosition()).getTimings().get(0).getEndTime();
//                String startTime = list.get(getAdapterPosition()).getTimings().get(0).getStartTime();
//
//                DateFormat sdf = new SimpleDateFormat("HH:mm");
//                Date sT=null;
//                Date cD = null;
//                Date eT = null;
//                try {
//                    cD = sdf.parse(currentDate);
//                    eT = sdf.parse(endtime);
//                    sT= sdf.parse(startTime);
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }


                try {
                    String string1 = list.get(getAdapterPosition()).getTimings().get(0).getStartTime().toString();
                    Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);

                    String string2 = list.get(getAdapterPosition()).getTimings().get(0).getEndTime().toString();
                    Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(time2);
                    calendar2.add(Calendar.DATE, 1);

                    String someRandomTime = currentTime;
                    Date d = new SimpleDateFormat("HH:mm").parse(someRandomTime);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(d);
                    calendar3.add(Calendar.DATE, 1);

                    Date x = calendar3.getTime();
                    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {

                        context.startActivity(new Intent(context, HotelViewActivity.class).putExtra("position", getAdapterPosition()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        GlobalData.selectedShop = list.get(getAdapterPosition());
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
                        list.get(getAdapterPosition()).getCuisines();
                    } else {
                        Activity activity = (Activity) context;

                        View parentLayout = activity.getWindow().getDecorView().findViewById(android.R.id.content);

                        Snackbar.make(parentLayout, "Restaurant Closed", Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(activity.getResources().getColor(android.R.color.holo_red_light))
                                .show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


//
//                    context.startActivity(new Intent(context, HotelViewActivity.class).putExtra("position", getAdapterPosition()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                    GlobalData.selectedShop = list.get(getAdapterPosition());
//                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.anim_nothing);
//                    list.get(getAdapterPosition()).getCuisines();

                }

            //
//                final String dateString = cursor.getString(4);
//                final String timeString = cursor.getString(5);
//                String datadb =dateString+" "+timeString;
//
//                if(currentDate.compareTo(datadb)>=0) {
//                    myCheckBox.setChecked(true);
//                    myCheckBox.setEnabled(false);
//                }

//                Toast.makeText(context, currentDate, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, list.get(getAdapterPosition()).getTimings().get(0).getEndTime(), Toast.LENGTH_SHORT).show();


        }
    }
//
//    boolean isTimeAfter(Date currentTime, Date endTime, Date startTime) {
//        if (currentTime.before(endTime) && currentTime.after(startTime)) { //Same way you can check with after() method also.
//            return false;
//        } else {
//            return true;
//        }
//    }


}
