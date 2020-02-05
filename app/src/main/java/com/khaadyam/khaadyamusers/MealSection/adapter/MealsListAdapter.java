package com.khaadyam.khaadyamusers.MealSection.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.MealSection.Meal1;
import com.khaadyam.khaadyamusers.MealSection.Meals2;
import com.khaadyam.khaadyamusers.MealSection.response.ShopLunch;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class MealsListAdapter extends RecyclerView.Adapter<MealsListAdapter.MyViewHolder> {
    private List<ShopLunch> list;
    private Context context;
    private Activity activity;
    public static String shopId = "";
    public static String shopImage = "";
    public static String shopName = "";
    private SharedPrefrences sharedPrefrences;

    public MealsListAdapter(List<ShopLunch> list, Context con, Activity act) {
        this.list = list;
        this.context = con;
        this.activity = act;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meals_layout1, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(ShopLunch item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ShopLunch item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ShopLunch shops = list.get(position);

        Glide.with(context).load(shops.getAvatar())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(holder.dishImg);
        holder.restaurantName.setText(shops.getName());
        holder.itemView.setTag(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Activity activity = (Activity) context;
                Intent i = new Intent(context, Meals2.class);
                shopId = list.get(position).getId();
                shopImage = list.get(position).getAvatar();
                shopName = list.get(position).getName();
                Meal1.hide_r_v="0";
                activity.startActivity(i);
                activity.overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemView;
        private ImageView dishImg;
        private TextView restaurantName, category, offer, rating, restaurantInfo, price, distanceTime;


        private MyViewHolder(View view) {
            super(view);
            itemView = (LinearLayout) view.findViewById(R.id.item_view);
            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);

//            category = (TextView) view.findViewById(R.id.category);
//            offer = (TextView) view.findViewById(R.id.offer);
//            rating = (TextView) view.findViewById(R.id.rating);
//            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
//            distanceTime = (TextView) view.findViewById(R.id.distance_time);
//            price = (TextView) view.findViewById(R.id.price);
        }

    }


}
