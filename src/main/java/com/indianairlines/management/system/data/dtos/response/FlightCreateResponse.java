package com.indianairlines.management.system.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCreateResponse {

    private Long flightId;
    private String aircraftName;
    private String sourceCity;
    private String destinationCity;
    private Date departureTime;
    private Date arrivalTime;
}
