package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.data.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBooking(Booking booking);
}
