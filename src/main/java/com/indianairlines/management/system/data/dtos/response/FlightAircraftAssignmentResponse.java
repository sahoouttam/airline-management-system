package com.indianairlines.management.system.data.dtos.response;

import com.indianairlines.management.system.data.enums.FlightStatus;
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
public class FlightAircraftAssignmentResponse {

    private String aircraftModel;
    private FlightStatus flightStatus;
    private int numberOfCrewMembers;
    private int aircraftSeatCapacity;
}
