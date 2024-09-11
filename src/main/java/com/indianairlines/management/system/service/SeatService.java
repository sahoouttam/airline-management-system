package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.data.entities.Flight;
import com.indianairlines.management.system.data.entities.Seat;
import com.indianairlines.management.system.data.enums.SeatType;
import com.indianairlines.management.system.exception.SeatAvailableException;
import com.indianairlines.management.system.exception.SeatNotAvailableException;
import com.indianairlines.management.system.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat allocateSeat(Flight flight, SeatType seatType) {
        Optional<Seat> seat = seatRepository
                .findByFlightAndSeatTypeAndIsAvailable(flight, seatType, true);
        if (seat.isEmpty()) {
            throw new SeatNotAvailableException("Seat not available");
        }
        Seat availableSeat = seat.get();
        availableSeat.setAvailable(false);
        return seatRepository.save(availableSeat);
    }

    public Seat unallocateSeat(Flight flight, SeatType seatType, String seatNumber) {
        Optional<Seat> seat = seatRepository
                .findByFlightAndSeatTypeAndSeatNumber(flight, seatType, seatNumber);
        if (seat.isEmpty()) {
            throw new SeatNotAvailableException("Seat not available");
        }
        if (!seat.get().isAvailable()) {
            throw new SeatAvailableException("Seat already unallocated");
        }
        Seat availableSeat = seat.get();
        availableSeat.setAvailable(true);
        return seatRepository.save(availableSeat);
    }
}
