package com.indianairlines.management.system.data.dtos.response;

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
public class FlightBookingChangeResponse {

    private Long bookingId;

    private String source;

    private String destination;

    private Date flightDate;

    private String seatNumber;
}
