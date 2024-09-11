package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.data.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByFlight(Flight flight);
}
