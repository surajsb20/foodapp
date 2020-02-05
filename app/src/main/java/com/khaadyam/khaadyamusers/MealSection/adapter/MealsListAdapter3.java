package com.khaadyam.khaadyamusers.MealSection.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.MealSection.response.MenuResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class MealsListAdapter3 extends RecyclerView.Adapter<MealsListAdapter3.MyViewHolder> {
    private List<MenuResponse> list;
    private Context context;
    private Activity activity;
    private SharedPrefrences sharedPrefrences;

    public MealsListAdapter3(List<MenuResponse> list, Context con, Activity act) {
        this.list = list;
        this.context = con;
        this.activity = act;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(MenuResponse item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(MenuResponse item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.restaurantName.setText(list.get(position).getItem_key().toUpperCase());
        holder.category.setText(list.get(position).getItem_value());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView itemView;
        private ImageView dishImg;
        private TextView restaurantName, category, offer, rating, restaurantInfo, price, distanceTime;


        private MyViewHolder(View view) {
            super(view);
            itemView =  view.findViewById(R.id.card);
//            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            restaurantName = (TextView) view.findViewById(R.id.key);
            category = (TextView) view.findViewById(R.id.value);
//            offer = (TextView) view.findViewById(R.id.offer);
//            rating = (TextView) view.findViewById(R.id.rating);
//            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
//            distanceTime = (TextView) view.findViewById(R.id.distance_time);
//            price = (TextView) view.findViewById(R.id.price);
        }

    }


}
