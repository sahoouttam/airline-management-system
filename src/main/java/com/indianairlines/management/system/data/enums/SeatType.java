package com.indianairlines.management.system.data.enums;

public enum SeatType {

    BUSINESS("BUSINESS"),
    ECONOMY("ECONOMY"),
    FIRST_CLASS("FIRST_CLASS");

    private String type;

    SeatType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
