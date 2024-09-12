package com.indianairlines.management.system.data.dtos.request;

import com.indianairlines.management.system.data.enums.SeatType;
import com.indianairlines.management.system.data.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightBookingChangeRequest {

    private Long passengerId;

    private String newSource;

    private String newDestination;

    private Date newFlightDate;

    private SeatType seatType;

    private String seatNumber;

    private double bookingFee;

    private String accountNumber;

    private TransactionType transactionType;
}
