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
import com.khaadyam.khaadyamusers.MealSection.Meals3;
import com.khaadyam.khaadyamusers.MealSection.response.Meals;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class MealsListAdapter1 extends RecyclerView.Adapter<MealsListAdapter1.MyViewHolder> {
    private List<Meals> list;
    private Context context;
    private Activity activity;
    public static String mealId = "";
    public static String shopImage = "";
    public static String mealName = "";
    private SharedPrefrences sharedPrefrences;
    public static String meal_image;

    public MealsListAdapter1(List<Meals> list, Context con, Activity act) {
        this.list = list;
        this.context = con;
        this.activity = act;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meals_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(Meals item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Meals item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.restaurantName.setText(list.get(position).getName());
        holder.category.setText(list.get(position).getDay());
        holder.menu_item.setText("MENU : "+list.get(position).getItem().toUpperCase());
        holder.menu_date.setText("DATE : "+list.get(position).getDate());
        Glide.with(context).load(list.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error((R.drawable.ic_restaurant_place_holder))
                .into(holder.dishImg);
        holder.itemView.setTag(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Activity activity = (Activity) context;
                Intent i = new Intent(context, Meals3.class);

                mealId = list.get(position).getId();
                mealName = list.get(position).getName();
                meal_image = list.get(position).getImage();
                activity.startActivity(i);
                activity.overridePendingTransition(R.anim.anim_push_left_in, R.anim.anim_nothing);

            }
        });
//        holder.cop.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemView;
        private ImageView dishImg;
        private TextView restaurantName, category, offer, rating, restaurantInfo, price, distanceTime;
        private final TextView menu_item,menu_date;
        //        private final TextView cop;


        private MyViewHolder(View view) {
            super(view);
            itemView = (LinearLayout) view.findViewById(R.id.item_view);
            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            restaurantName = (TextView) view.findViewById(R.id.restaurant_name);
            category = (TextView) view.findViewById(R.id.category);
            menu_item = itemView.findViewById(R.id.menu_item);
            menu_date = itemView.findViewById(R.id.menu_date);
//            cop = view.findViewById(R.id.cop);
//            offer = (TextView) view.findViewById(R.id.offer);
//            rating = (TextView) view.findViewById(R.id.rating);
//            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
//            distanceTime = (TextView) view.findViewById(R.id.distance_time);
//            price = (TextView) view.findViewById(R.id.price);
        }

    }


}
