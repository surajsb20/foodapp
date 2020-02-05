package com.khaadyam.khaadyamusers.MealSection.response;

public class TimeSlotResponse {
    String id,ts;

    public TimeSlotResponse(String id, String ts) {
        this.id = id;
        this.ts = ts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
