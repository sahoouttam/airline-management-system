package com.indianairlines.management.system.data.enums;

public enum AircraftManufacturer {

    AIR_BUS("AIR_BUS"),
    ANTONOV("ANTONOV"),
    BOEING("BOEING"),
    EMBRAER("EMBRAER");

    private String model;

    AircraftManufacturer(String model) {
        this.model = model;
    }

    public String toString() {
        return model;
    }
}
