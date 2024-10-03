package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.AirportCreateRequest;
import com.indianairlines.management.system.data.dtos.response.AirportCreateResponse;
import com.indianairlines.management.system.data.entities.Airport;
import com.indianairlines.management.system.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/airports/create")
    public ResponseEntity<AirportCreateResponse> createAirport(@RequestBody AirportCreateRequest airportCreateRequest) {
        AirportCreateResponse airportCreateResponse = airportService
                .createAirport(airportCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(airportCreateResponse);
    }
}
