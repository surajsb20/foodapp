package com.khaadyam.khaadyamusers.chatUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PushNotifyModel {
    @SerializedName("device_token")
    @Expose
    private String device_token;

    public PushNotifyModel(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
