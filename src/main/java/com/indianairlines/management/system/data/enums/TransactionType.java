package com.indianairlines.management.system.data.enums;

public enum TransactionType {

    DEBIT("DEBIT"),
    CREDIT("CREDIT"),
    UPI("UPI"),
    CASH("CASH");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
