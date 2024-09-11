package com.indianairlines.management.system.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCancelledResponse {

    private Long bookingId;
    private double amountRefunded;
    private String seatNumber;
}
