package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.dtos.request.BaggageCreateRequest;
import com.indianairlines.management.system.data.dtos.response.BaggageCreateResponse;
import com.indianairlines.management.system.data.entities.Baggage;
import com.indianairlines.management.system.data.entities.Passenger;
import com.indianairlines.management.system.repository.BaggageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaggageService {

    private final BaggageRepository baggageRepository;
    private final PassengerService passengerService;

    @Autowired
    public BaggageService(BaggageRepository baggageRepository,
                          PassengerService passengerService) {
        this.baggageRepository = baggageRepository;
        this.passengerService = passengerService;
    }

    public BaggageCreateResponse createBaggage(BaggageCreateRequest baggageCreateRequest) {
        Passenger passenger = passengerService.getPassengerById(baggageCreateRequest.getPassengerId());
        Baggage baggageCreated = Baggage.builder()
                .baggageType(baggageCreateRequest.getBaggageType())
                .weight(baggageCreateRequest.getWeight())
                .tagCode(baggageCreateRequest.getTagCode())
                .description(baggageCreateRequest.getDescription())
                .passenger(passenger)
                .build();

        baggageRepository.save(baggageCreated);
        return new BaggageCreateResponse(baggageCreateRequest.getBaggageType(), baggageCreated.getWeight(), baggageCreateRequest.getTagCode());
    }


}
