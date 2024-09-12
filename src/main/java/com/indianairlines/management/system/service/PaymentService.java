package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.data.entities.Passenger;
import com.indianairlines.management.system.data.entities.Payment;
import com.indianairlines.management.system.data.enums.PaymentStatus;
import com.indianairlines.management.system.data.enums.TransactionType;
import com.indianairlines.management.system.exception.PaymentNotFoundException;
import com.indianairlines.management.system.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountService accountService;
    private final PassengerService passengerService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          AccountService accountService,
                          PassengerService passengerService) {
        this.paymentRepository = paymentRepository;
        this.accountService = accountService;
        this.passengerService = passengerService;
    }

    @Transactional
    public Payment processPayment(Booking booking, TransactionType transactionType,
                                  double amount, String accountNumber) {
        Passenger passenger = passengerService.getPassengerByBooking(booking);
        Payment payment = Payment.builder()
                .booking(booking)
                .transactionDate(new Date())
                .transactionType(transactionType)
                .transactionAmount(amount)
                .paymentStatus(PaymentStatus.PAID)
                .build();
        Payment paidPayment = paymentRepository.save(payment);
        accountService.debitAmount(passenger, amount, accountNumber);
        return paidPayment;
    }

    @Transactional
    public Payment processRefund(Booking booking, String accountNumber) {
        Passenger passenger = passengerService.getPassengerByBooking(booking);
        Payment payment = getByBooking(booking);
        if (PaymentStatus.REFUND.equals(payment.getPaymentStatus())) {
            throw new PaymentNotFoundException("Payment refund already processed");
        }
        payment.setPaymentStatus(PaymentStatus.REFUND);
        Payment paymentRefund = paymentRepository.save(payment);
        accountService.creditAmount(passenger,
                payment.getTransactionAmount(),
                accountNumber);
        return paymentRefund;
    }

    public Payment getByBooking(Booking booking) {
        Optional<Payment> payment = paymentRepository.findByBooking(booking);
        if (payment.isEmpty()) {
            throw new PaymentNotFoundException("Payment not found");
        }
        return payment.get();
    }
}
