package com.indianairlines.management.system.exception;

public class BookingCancelledException extends RuntimeException {
    public BookingCancelledException(String message) {
        super(message);
    }
}
