package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.dtos.request.AirportCreateRequest;
import com.indianairlines.management.system.data.dtos.response.AirportCreateResponse;
import com.indianairlines.management.system.data.entities.Airport;
import com.indianairlines.management.system.exception.AirportNotFoundException;
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

    public AirportCreateResponse createAirport(AirportCreateRequest airportCreateRequest) {
        Airport airport = Airport.builder()
                .name(airportCreateRequest.getName())
                .city(airportCreateRequest.getCity())
                .country(airportCreateRequest.getCountry())
                .airportCode(airportCreateRequest.getAirportCode())
                .build();
        Airport createdAirport = airportRepository.save(airport);
        return new AirportCreateResponse(createdAirport.getName(),
                createdAirport.getCity(),
                createdAirport.getCountry());
    }

    public Airport findAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(
                        "Airport not found with id " + id));
    }

}
