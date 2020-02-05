package com.khaadyam.khaadyamusers.chatUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderResponse {

    @SerializedName("data")
    @Expose
    private List<SliderModel> sliderModelList = null;

    public List<SliderModel> getSlider() {
        return sliderModelList;
    }

    public void setSlider(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
}
