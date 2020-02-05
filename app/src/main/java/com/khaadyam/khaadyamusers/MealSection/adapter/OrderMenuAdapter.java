package com.khaadyam.khaadyamusers.MealSection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.MealSection.OrderMenuActivity;
import com.khaadyam.khaadyamusers.MealSection.response.OrderMenuResponse;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.sharedpreferences.SharedPrefrences;

import java.util.Calendar;
import java.util.List;

/**
 * Created by santhosh@appoets.com on 22-08-2017.
 */

public class OrderMenuAdapter extends RecyclerView.Adapter<OrderMenuAdapter.MyViewHolder> {
    private List<OrderMenuResponse> list;
    private Context context;
    //    private Activity activity;
    private SharedPrefrences sharedPrefrences;
    final Calendar myCalendar = Calendar.getInstance();
    OrderMenuActivity mealOrderActivity;

    public OrderMenuAdapter(List<OrderMenuResponse> list, Context con, OrderMenuActivity mealOrderActivity) {
        this.list = list;
        this.context = con;
        this.mealOrderActivity = mealOrderActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_menu_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    public void add(OrderMenuResponse item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(OrderMenuResponse item) {
        int position = list.indexOf(item);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        OrderMenuResponse shops = list.get(position);
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

        holder.name.setText(list.get(position).getName());
        holder.day.setText(list.get(position).getDay());
        holder.date.setText(list.get(position).getCreated_at());
        holder.menu.setText(list.get(position).getMenu());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView day, date, created_at, menu, name;


        private MyViewHolder(View view) {
            super(view);

            date = (TextView) view.findViewById(R.id.date);
            day = (TextView) view.findViewById(R.id.day);
            created_at = (TextView) view.findViewById(R.id.created_at);
            name = (TextView) view.findViewById(R.id.name);
            menu = (TextView) view.findViewById(R.id.menu);

        }

    }


}

