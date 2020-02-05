package com.khaadyam.khaadyamusers.MealSection.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.MealSection.MealOrderActivity;
import com.khaadyam.khaadyamusers.MealSection.OrderMenuActivity;
import com.khaadyam.khaadyamusers.MealSection.response.OrderMealResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.constants.URLConstants;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.Calendar;
import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class MealOrderAdapter extends RecyclerView.Adapter<MealOrderAdapter.MyViewHolder> {
    private List<OrderMealResponse> list;
    private Context context;
    //    private Activity activity;
    private SharedPrefrences sharedPrefrences;
    final Calendar myCalendar = Calendar.getInstance();
    public static String meal_order_id;
    public static String start_date;
    public static String end_date;
    public static String meal_shop_id;
    MealOrderActivity mealOrderActivity;

    public MealOrderAdapter(List<OrderMealResponse> list, Context con, MealOrderActivity mealOrderActivity) {
        this.list = list;
        this.context = con;
        this.mealOrderActivity = mealOrderActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_meal_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(OrderMealResponse item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(OrderMealResponse item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        OrderMealResponse shops = list.get(position);
//
//        Glide.with(context).load(shops.getAvatar())
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error((R.drawable.ic_restaurant_place_holder))
//                .into(holder.dishImg);
//        holder.restaurantName.setText(shops.getName());
//        holder.itemView.setTag(list.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Activity activity = (Activity) context;
//                Intent i = new Intent(context, Meals2.class);
//                shopId = list.get(position).getId();
//                shopImage = list.get(position).getAvatar();
//                shopName = list.get(position).getName();
//
//                activity.startActivity(i);
//                activity.overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);
//
//            }
//        });

        holder.name.setText(shops.getName());
        holder.start_date_end_date.setText("From : " + list.get(position).getStart_date() + " To : " + list.get(position).getEnd_date());
        holder.total_payable.setText(URLConstants.Rs + " " + list.get(position).getTotal_payable());
        holder.total_days_type_time_slot.setText("You Have Booked: " + list.get(position).getType() + " for " + list.get(position).getTotal_days() + " days");
        holder.created_at.setText("You Have Booked on: " + list.get(position).getCreated_at());

        holder.viewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meal_order_id = list.get(position).getId();
                start_date=list.get(position).getStart_date();
                end_date=list.get(position).getEnd_date();
                meal_shop_id=list.get(position).getShop_id();
                mealOrderActivity.startActivity(new Intent(context, OrderMenuActivity.class));
                mealOrderActivity.finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        private LinearLayout itemView;
//        private ImageView dishImg;
        private TextView total_days_type_time_slot, start_date_end_date, created_at, total_payable, name;
        private final TextView viewmenu;


        private MyViewHolder(View view) {
            super(view);
//            itemView = (LinearLayout) view.findViewById(R.id.item_view);
//            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            total_days_type_time_slot = (TextView) view.findViewById(R.id.type_time_slot);
            start_date_end_date = (TextView) view.findViewById(R.id.date);
            created_at = (TextView) view.findViewById(R.id.created_at);
            created_at = (TextView) view.findViewById(R.id.created_at);
            total_payable = (TextView) view.findViewById(R.id.total_payable);
            name = (TextView) view.findViewById(R.id.name);
            viewmenu = view.findViewById(R.id.view_menu);

//            category = (TextView) view.findViewById(R.id.category);
//            offer = (TextView) view.findViewById(R.id.offer);
//            rating = (TextView) view.findViewById(R.id.rating);
//            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
//            distanceTime = (TextView) view.findViewById(R.id.distance_time);
//            price = (TextView) view.findViewById(R.id.price);
        }

    }


}

