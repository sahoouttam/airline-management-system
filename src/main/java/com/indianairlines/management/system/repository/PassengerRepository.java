package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.data.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Passenger> findByBooking(Booking booking);
}
