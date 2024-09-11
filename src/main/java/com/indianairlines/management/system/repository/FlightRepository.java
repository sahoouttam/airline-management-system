package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Airport;
import com.indianairlines.management.system.data.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<List<Flight>> findBySourceAndDestinationAndSourceDepartureTime(
            Airport source, Airport destination, Date departureTime);

    Optional<Flight> findByIdAndSourceAndDestinationAndSourceDepartureTime(
            Long flightId, Airport source, Airport destination, Date departureTime);
}
