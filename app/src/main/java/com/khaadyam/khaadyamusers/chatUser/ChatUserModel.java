package com.khaadyam.khaadyamusers.chatUser;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tamil on 9/22/2017.
 */

public class ChatUserModel {

    @SerializedName("cud_msg")
    @Expose
    private String cud_msg;
    @SerializedName("cud_order_id")
    @Expose
    private String cud_order_id;
    @SerializedName("msg_by")
    @Expose
    private String msg_by;
    @SerializedName("cud_date")
    @Expose
    private String cud_date;

    public String getCud_msg() {
        return cud_msg;
    }

    public void setCud_msg(String cud_msg) {
        this.cud_msg = cud_msg;
    }

    public String getCud_order_id() {
        return cud_order_id;
    }

    public void setCud_order_id(String cud_order_id) {
        this.cud_order_id = cud_order_id;
    }

    public String getMsg_by() {
        return msg_by;
    }

    public void setMsg_by(String msg_by) {
        this.msg_by = msg_by;
    }

    public String getCud_date() {
        return cud_date;
    }

    public void setCud_date(String cud_date) {
        this.cud_date = cud_date;
    }
}



