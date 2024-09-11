package com.indianairlines.management.system.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightBookingResponse {

    private Long bookingId;
    private String source;
    private String destination;
    private String seatNumber;
    private String seatType;
    private String transactionType;
    private double ticketFare;
}
