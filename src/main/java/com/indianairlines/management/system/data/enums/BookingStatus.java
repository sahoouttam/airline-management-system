package com.indianairlines.management.system.data.enums;

public enum BookingStatus {

    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    private String status;

    BookingStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
