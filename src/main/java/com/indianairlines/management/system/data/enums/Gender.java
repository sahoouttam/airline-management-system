package com.indianairlines.management.system.data.enums;

public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return gender;
    }
}
