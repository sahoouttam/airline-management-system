package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<List<Airport>> findByCity(String city);
}
