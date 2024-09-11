package com.indianairlines.management.system.data.enums;

public enum MemberType {
    ADMIN("ADMIN"),
    PILOT("PILOT"),
    CO_PILOT("CO_PILOT"),
    FLIGHT_ATTENDANT("FLIGHT_ATTENDANT"),
    CABIN_CREW("CABIN_CREW");

    private String crew;

    MemberType(String crew) {
        this.crew = crew;
    }

    public String toString() {
        return crew;
    }
}
