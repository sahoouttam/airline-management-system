package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.FlightAircraftAssignmentRequest;
import com.indianairlines.management.system.data.dtos.request.FlightSearchRequest;
import com.indianairlines.management.system.data.dtos.response.FlightAircraftAssignmentResponse;
import com.indianairlines.management.system.data.dtos.response.FlightSearchResponse;
import com.indianairlines.management.system.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights/search/source/{source}/destination/{destination}/date{flightDate}")
    public ResponseEntity<List<FlightSearchResponse>> searchFlights(@PathVariable String source,
                                                                    @PathVariable String destination,
                                                                    @PathVariable Date flightDate) {
        FlightSearchRequest flightSearchRequest = FlightSearchRequest.builder()
                .sourceCity(source)
                .destinationCity(destination)
                .schedulingDate(flightDate)
                .build();
        List<FlightSearchResponse> flightSearchResponses = flightService.searchFlight(flightSearchRequest);
        return new ResponseEntity<>(flightSearchResponses, HttpStatus.OK);
    }

    @PatchMapping("/flights/assign/{flightId}")
    public ResponseEntity<FlightAircraftAssignmentResponse> assignFlight(@PathVariable Long flightId,
                                                                         @RequestBody FlightAircraftAssignmentRequest
                                                                                 flightAircraftAssignmentRequest) {
        FlightAircraftAssignmentResponse flightAircraftAssignmentResponse = flightService
                .assignAircraftAndCrewMembers(flightId, flightAircraftAssignmentRequest);
        return new ResponseEntity<>(flightAircraftAssignmentResponse, HttpStatus.OK);
    }
}
