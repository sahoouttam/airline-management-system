package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.entities.Airport;
import com.indianairlines.management.system.repository.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Optional<List<Airport>> getAllAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }
}
