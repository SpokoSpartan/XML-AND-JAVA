package com.spot.on.demo.models;

import java.util.List;

public class Metadata{
    private List<Station> stations;
    private String reading_type;
    private String reading_unit;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String getReading_type() {
        return reading_type;
    }

    public void setReading_type(String reading_type) {
        this.reading_type = reading_type;
    }

    public String getReading_unit() {
        return reading_unit;
    }

    public void setReading_unit(String reading_unit) {
        this.reading_unit = reading_unit;
    }
}
