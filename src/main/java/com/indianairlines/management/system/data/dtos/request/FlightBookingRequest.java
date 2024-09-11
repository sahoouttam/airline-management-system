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
public class FlightBookingRequest {

    private Long passengerId;

    private String source;

    private String destination;

    private Date flightDate;

    private SeatType seatType;

    private double bookingFee;

    private TransactionType transactionType;
}
