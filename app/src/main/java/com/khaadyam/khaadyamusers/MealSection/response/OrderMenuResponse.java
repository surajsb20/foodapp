package com.khaadyam.khaadyamusers.MealSection.response;

public class OrderMenuResponse {
    String name,created_at,day,menu;

    public OrderMenuResponse(String name, String created_at, String day, String menu) {
        this.name = name;
        this.created_at = created_at;
        this.day = day;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
