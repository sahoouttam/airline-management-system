package com.indianairlines.management.system.data.dtos.response;

import com.indianairlines.management.system.data.enums.AircraftManufacturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResponse {

    private Date departureTime;
    private Date arrivalTime;
    private AircraftManufacturer aircraftManufacturer;
    private String model;
    private int seatCapacity;
}
