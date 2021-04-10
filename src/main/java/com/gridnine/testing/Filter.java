package com.gridnine.testing;

public abstract class Filter implements FlightFilter{
    private String description;

    public Filter(String description) {
       this.description=description;
    }

    public String getDescription() {
        return description;
    }
}
