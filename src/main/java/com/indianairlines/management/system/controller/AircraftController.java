package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.AircraftRegisterRequest;
import com.indianairlines.management.system.data.dtos.response.AircraftRegisterResponse;
import com.indianairlines.management.system.service.AircraftService;
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
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping("/aircraft/register")
    public ResponseEntity<AircraftRegisterResponse> registerAircraft(@RequestBody AircraftRegisterRequest aircraftRegisterRequest) {
        AircraftRegisterResponse aircraftRegisterResponse = aircraftService
                .registerAircraft(aircraftRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraftRegisterResponse);
    }
}
