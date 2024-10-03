package com.indianairlines.management.system.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCreateRequest {

    private Long aircraftId;
    private Long sourceId;
    private Long destinationId;
    private Date departureTime;
    private Date arrivalTime;
    private String flightStatus;
}
