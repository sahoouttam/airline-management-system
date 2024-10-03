package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.PassengerRegisterRequest;
import com.indianairlines.management.system.data.dtos.response.PassengerRegisterResponse;
import com.indianairlines.management.system.data.dtos.response.PassengerResponse;
import com.indianairlines.management.system.data.enums.Gender;
import com.indianairlines.management.system.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/passengers/phone/{phoneNumber}/gender/{gender}")
    public ResponseEntity<PassengerResponse> getPassenger(@PathVariable String phoneNumber,
                                                          @PathVariable Gender gender) {
        PassengerResponse passengerResponse = passengerService.getPassenger(phoneNumber, gender);
        return new ResponseEntity<>(passengerResponse, HttpStatus.OK);
    }

}
