package com.khaadyam.khaadyamusers.MealSection.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.MealSection.Meals4;
import com.khaadyam.khaadyamusers.MealSection.response.TimeSlotResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder> {
    private List<TimeSlotResponse> list;
    private Context context;
    private Meals4 meals4;
    private SharedPrefrences sharedPrefrences;
    public static String ts_id;
    private int row_index=999;

    public TimeSlotAdapter(List<TimeSlotResponse> list, Context con, Meals4 meals4) {
        this.list = list;
        this.context = con;
        this.meals4 = meals4;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_slot_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(TimeSlotResponse item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(TimeSlotResponse item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.restaurantName.setText(list.get(position).getTs());

        holder.itemView.setTag(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                ts_id = list.get(position).getId();
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.restaurantName.setTextColor(Color.parseColor("#FD0808"));
//            holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.restaurantName.setTextColor(Color.parseColor("#000000"));
//            holder.tv1.setTextColor(Color.parseColor("#000000"));
        }

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
            itemView = view.findViewById(R.id.card);
//            dishImg = (ImageView) view.findViewById(R.id.dish_img);
            restaurantName = (TextView) view.findViewById(R.id.ts);
//            offer = (TextView) view.findViewById(R.id.offer);
//            rating = (TextView) view.findViewById(R.id.rating);
//            restaurantInfo = (TextView) view.findViewById(R.id.restaurant_info);
//            distanceTime = (TextView) view.findViewById(R.id.distance_time);
//            price = (TextView) view.findViewById(R.id.price);
        }

    }


}
