package com.khaadyam.khaadyamusers.chatUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderModel {
    @SerializedName("image")
    @Expose
    private String image;

    public SliderModel(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
