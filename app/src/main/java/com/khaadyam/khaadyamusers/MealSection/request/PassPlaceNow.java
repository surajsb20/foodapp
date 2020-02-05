package com.khaadyam.khaadyamusers.MealSection.request;

public class PassPlaceNow {
    String user_id,shop_id,total_days,start_date,end_date,time_slot,total_payable,address,payment_id;

    public PassPlaceNow(String user_id, String shop_id, String total_days, String start_date, String end_date, String time_slot, String total_payable, String address, String payment_id) {
        this.user_id = user_id;
        this.shop_id = shop_id;
        this.total_days = total_days;
        this.start_date = start_date;
        this.end_date = end_date;
        this.time_slot = time_slot;
        this.total_payable = total_payable;
        this.address = address;
        this.payment_id = payment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTotal_days() {
        return total_days;
    }

    public void setTotal_days(String total_days) {
        this.total_days = total_days;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getTotal_payable() {
        return total_payable;
    }

    public void setTotal_payable(String total_payable) {
        this.total_payable = total_payable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
}
