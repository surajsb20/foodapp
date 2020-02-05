package com.khaadyam.khaadyamusers.MealSection.request;

public class PassThreeData
{
    String id1,id2,id3;

    public PassThreeData(String id1, String id2, String id3) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }
}
