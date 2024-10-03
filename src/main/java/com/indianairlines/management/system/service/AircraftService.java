package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.dtos.request.AircraftRegisterRequest;
import com.indianairlines.management.system.data.dtos.response.AircraftRegisterResponse;
import com.indianairlines.management.system.data.entities.Aircraft;
import com.indianairlines.management.system.data.enums.AircraftManufacturer;
import com.indianairlines.management.system.repository.AircraftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    @Autowired
    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft getById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId)
                .orElseThrow(() -> new EntityNotFoundException("Aircraft not found"));
    }

    public AircraftRegisterResponse registerAircraft(AircraftRegisterRequest aircraftRegisterRequest) {
        Aircraft aircraft = Aircraft.builder()
                .aircraftManufacturer(AircraftManufacturer.valueOf(aircraftRegisterRequest.getAircraftManufacturer()))
                .model(aircraftRegisterRequest.getModel())
                .registrationNumber(aircraftRegisterRequest.getRegistrationNumber())
                .seatCapacity(aircraftRegisterRequest.getSeatCapacity())
                .build();
        Aircraft registeredAircraft = saveAircraft(aircraft);
        return new AircraftRegisterResponse(
                registeredAircraft.getAircraftManufacturer(),
                registeredAircraft.getModel(),
                registeredAircraft.getRegistrationNumber()
        );
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

}
