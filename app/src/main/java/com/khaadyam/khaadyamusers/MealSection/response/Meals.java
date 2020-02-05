package com.khaadyam.khaadyamusers.MealSection.response;

public class Meals {
    String id,image,name,day,item,date;

    public Meals(String id, String image, String name, String day, String item, String date) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.day = day;
        this.item = item;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
