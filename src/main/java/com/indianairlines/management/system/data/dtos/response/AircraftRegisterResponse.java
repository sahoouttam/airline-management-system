package com.indianairlines.management.system.data.dtos.response;


import com.indianairlines.management.system.data.enums.AircraftManufacturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftRegisterResponse {

    private AircraftManufacturer aircraftManufacturer;

    private String model;

    private String registrationNumber;
}
