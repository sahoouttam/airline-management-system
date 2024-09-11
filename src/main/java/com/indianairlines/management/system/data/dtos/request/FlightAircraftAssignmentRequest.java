package com.indianairlines.management.system.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightAircraftAssignmentRequest {

    private Long aircraftId;
    private List<Long> crewMemberIds;
    private String source;
    private String destination;
    private Date flightDate;
}
