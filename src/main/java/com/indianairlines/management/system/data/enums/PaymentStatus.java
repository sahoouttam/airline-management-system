package com.indianairlines.management.system.data.enums;

public enum PaymentStatus {

    PAID("PAID"),
    REFUND("REFUND");

    private String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
