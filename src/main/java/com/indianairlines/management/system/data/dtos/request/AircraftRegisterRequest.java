package com.indianairlines.management.system.data.dtos.request;


import com.indianairlines.management.system.data.enums.AircraftManufacturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftRegisterRequest {

    private String aircraftManufacturer;

    private String model;

    private String registrationNumber;

    private int seatCapacity;
}
