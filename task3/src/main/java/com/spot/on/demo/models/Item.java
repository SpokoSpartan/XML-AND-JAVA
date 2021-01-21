package com.spot.on.demo.models;

import java.util.Date;
import java.util.List;

public class Item{
    private Date timestamp;
    private List<Reading> readings;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }
}
