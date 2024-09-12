package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.BaggageCreateRequest;
import com.indianairlines.management.system.data.dtos.response.BaggageCreateResponse;
import com.indianairlines.management.system.service.BaggageService;
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
public class BaggageController {

    private final BaggageService baggageService;

    @Autowired
    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @PostMapping("/baggage/create")
    public ResponseEntity<BaggageCreateResponse> createBaggage(@RequestBody BaggageCreateRequest baggageCreateRequest) {
        BaggageCreateResponse baggageCreateResponse = baggageService.createBaggage(baggageCreateRequest);
        return new ResponseEntity<>(baggageCreateResponse, HttpStatus.CREATED);
    }
}
