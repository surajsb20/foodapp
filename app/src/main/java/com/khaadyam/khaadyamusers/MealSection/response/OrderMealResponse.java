package com.khaadyam.khaadyamusers.MealSection.response;

public class OrderMealResponse {
    String id, shop_id,total_days,start_date,end_date,created_at,total_payable,name,time_slot,type;

    public OrderMealResponse(String id, String shop_id, String total_days, String start_date, String end_date, String created_at, String total_payable, String name, String time_slot, String type) {
        this.id = id;
        this.shop_id = shop_id;
        this.total_days = total_days;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_at = created_at;
        this.total_payable = total_payable;
        this.name = name;
        this.time_slot = time_slot;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTotal_payable() {
        return total_payable;
    }

    public void setTotal_payable(String total_payable) {
        this.total_payable = total_payable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
