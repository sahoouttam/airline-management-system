package com.indianairlines.management.system.data.enums;

public enum BaggageType {

    CHECKED("CHECKED"),
    CABIN("CABIN");

    private String type;

    BaggageType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}
