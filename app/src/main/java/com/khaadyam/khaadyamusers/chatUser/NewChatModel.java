package com.khaadyam.khaadyamusers.chatUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewChatModel {
    @SerializedName("chats")
    @Expose
    private List<ChatUserModel> chats = null;

    @SerializedName("cud_order_id")
    @Expose
    private String cud_order_id;

    public List<ChatUserModel> getChats() {
        return chats;
    }

    public void setChats(List<ChatUserModel> chats) {
        this.chats = chats;
    }

    public String getCud_order_id() {
        return cud_order_id;
    }

    public void setCud_order_id(String cud_order_id) {
        this.cud_order_id = cud_order_id;
    }
}
