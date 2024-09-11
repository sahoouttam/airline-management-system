package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Flight;
import com.indianairlines.management.system.data.entities.Seat;
import com.indianairlines.management.system.data.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByFlightAndSeatTypeAndIsAvailable(
            Flight flight, SeatType seatType, boolean isAvailable);

    Optional<Seat> findByFlightAndSeatTypeAndSeatNumber(
            Flight flight, SeatType seatType, String seatNumber);

}
