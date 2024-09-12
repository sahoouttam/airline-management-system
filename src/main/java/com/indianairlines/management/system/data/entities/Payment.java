package com.indianairlines.management.system.data.entities;

import com.indianairlines.management.system.data.enums.PaymentStatus;
import com.indianairlines.management.system.data.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private Date transactionDate;

    private TransactionType transactionType;

    private double transactionAmount;

    private PaymentStatus paymentStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Version
    private Long version;
}
