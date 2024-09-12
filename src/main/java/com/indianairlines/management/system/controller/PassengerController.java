package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.PassengerRegisterRequest;
import com.indianairlines.management.system.data.dtos.response.PassengerRegisterResponse;
import com.indianairlines.management.system.service.PassengerService;
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
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/passengers/register")
    public ResponseEntity<PassengerRegisterResponse> register(@RequestBody PassengerRegisterRequest passengerRegisterRequest) {
        PassengerRegisterResponse passengerRegisterResponse = passengerService.register(passengerRegisterRequest);
        return new ResponseEntity<>(passengerRegisterResponse, HttpStatus.CREATED);
    }
}
