package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.dtos.request.PassengerRegisterRequest;
import com.indianairlines.management.system.data.dtos.response.PassengerRegisterResponse;
import com.indianairlines.management.system.data.dtos.response.PassengerResponse;
import com.indianairlines.management.system.data.entities.Passenger;
import com.indianairlines.management.system.data.enums.Gender;
import com.indianairlines.management.system.exception.PassengerNotFoundException;
import com.indianairlines.management.system.repository.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public PassengerRegisterResponse register(PassengerRegisterRequest passengerRegisterRequest) {
        Passenger passenger = Passenger.builder()
                .name(passengerRegisterRequest.getName())
                .gender(passengerRegisterRequest.getGender())
                .email(passengerRegisterRequest.getEmail())
                .address(passengerRegisterRequest.getAddress())
                .phoneNumber(passengerRegisterRequest.getPhoneNumber())
                .build();
        Passenger registeredPassenger = passengerRepository.save(passenger);
        log.info("Successfully registered passenger with id {}",
                registeredPassenger.getId());
        return new PassengerRegisterResponse(registeredPassenger.getName(),
                registeredPassenger.getPhoneNumber(),
                registeredPassenger.getAddress());
    }

    public PassengerResponse getPassenger(String phoneNumber, Gender gender) {
        return passengerRepository.findByPhoneNumberAndGender(phoneNumber, gender)
                .map(this::getPassengerResponse)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with number " + phoneNumber));
    }

    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id " + passengerId));
    }

    private PassengerResponse getPassengerResponse(Passenger passenger) {
        return PassengerResponse.builder()
                .name(passenger.getName())
                .email(passenger.getEmail())
                .address(passenger.getAddress())
                .build();
    }

}
