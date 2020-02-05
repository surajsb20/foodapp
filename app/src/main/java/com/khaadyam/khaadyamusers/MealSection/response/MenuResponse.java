package com.khaadyam.khaadyamusers.MealSection.response;

public class MenuResponse {
    String item_key,item_value;

    public MenuResponse(String item_key, String item_value) {
        this.item_key = item_key;
        this.item_value = item_value;
    }

    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(String item_key) {
        this.item_key = item_key;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }
}
