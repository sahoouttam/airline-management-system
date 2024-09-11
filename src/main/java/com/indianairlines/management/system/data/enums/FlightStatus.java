package com.indianairlines.management.system.data.enums;

public enum FlightStatus {

    SCHEDULED("SCHEDULED"),
    DEPARTED("DEPARTED");

    private String status;

    FlightStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
