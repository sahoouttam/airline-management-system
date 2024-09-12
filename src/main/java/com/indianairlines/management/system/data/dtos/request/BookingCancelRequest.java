package com.indianairlines.management.system.data.dtos.request;

import com.indianairlines.management.system.data.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCancelRequest {

    private SeatType seatType;
    private String seatNumber;
    private String accountNumber;
}
