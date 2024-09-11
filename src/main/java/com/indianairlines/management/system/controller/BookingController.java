package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.BookingCancelRequest;
import com.indianairlines.management.system.data.dtos.request.FlightBookingRequest;
import com.indianairlines.management.system.data.dtos.response.BookingCancelledResponse;
import com.indianairlines.management.system.data.dtos.response.FlightBookingResponse;
import com.indianairlines.management.system.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/v1")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/confirm")
    public ResponseEntity<FlightBookingResponse> bookFlight(@RequestBody FlightBookingRequest flightBookingRequest) {
        log.info("Booking flight from {} to {}", flightBookingRequest.getSource(),
                flightBookingRequest.getDestination());
        FlightBookingResponse flightBookingResponse = bookingService
                .confirmBooking(flightBookingRequest);
        return new ResponseEntity<>(flightBookingResponse, HttpStatus.CREATED);
    }

    @PutMapping("/booking/cancel/{bookingId}")
    public ResponseEntity<BookingCancelledResponse> bookFlight(@PathVariable Long bookingId,
                                               @RequestBody BookingCancelRequest bookingCancelRequest) {
        log.info("Cancelling flight on {}", new Date());
        BookingCancelledResponse bookingCancelledResponse = bookingService
                .cancelBooking(bookingId, bookingCancelRequest);
        return new ResponseEntity<>(bookingCancelledResponse, HttpStatus.OK);
    }
}
