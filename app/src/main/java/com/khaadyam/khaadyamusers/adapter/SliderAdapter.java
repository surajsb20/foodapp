package com.khaadyam.khaadyamusers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.chatUser.SliderModel;


import java.util.List;
import java.util.Objects;

public class SliderAdapter extends PagerAdapter{
    Context context;
    List<SliderModel> sliderResponse;

    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context, List<SliderModel> sliderResponse) {
        this.context = context;
        this.sliderResponse=sliderResponse;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(layoutInflater).inflate(R.layout.slider_layout, null);


        ImageView imageView = (ImageView) view.findViewById(R.id.slider_image);


//        new DownloadImageFromInternet(imageView)
//                .execute(URLConstants.slider_image + sliderResponse.get(position).getSlider_image());
//
        Glide.with(context).load(sliderResponse.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public int getCount() {
        return sliderResponse.size();
    }



}  