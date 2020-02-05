package com.khaadyam.khaadyamusers.MealSection.response;

public class ShopLunch {
    String name,avatar,id;

    public ShopLunch(String name, String avatar, String id) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
