package com.indianairlines.management.system.controller;

import com.indianairlines.management.system.data.dtos.request.BookingCancelRequest;
import com.indianairlines.management.system.data.dtos.request.FlightBookingChangeRequest;
import com.indianairlines.management.system.data.dtos.request.FlightBookingRequest;
import com.indianairlines.management.system.data.dtos.response.BookingCancelledResponse;
import com.indianairlines.management.system.data.dtos.response.FlightBookingChangeResponse;
import com.indianairlines.management.system.data.dtos.response.FlightBookingResponse;
import com.indianairlines.management.system.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

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
    public FlightBookingResponse bookFlight(@RequestBody FlightBookingRequest flightBookingRequest) {
        log.info("Booking flight from {} to {}", flightBookingRequest.getSource(),
                flightBookingRequest.getDestination());
        return bookingService.confirmFlightBooking(flightBookingRequest);
    }

    @PutMapping("/booking/cancel/{bookingId}")
    public ResponseEntity<BookingCancelledResponse> bookFlight(@PathVariable Long bookingId,
                                               @RequestBody BookingCancelRequest bookingCancelRequest) {
        log.info("Cancelling flight on {}", new Date());
        BookingCancelledResponse bookingCancelledResponse = bookingService
                .cancelBooking(bookingId, bookingCancelRequest);
        return new ResponseEntity<>(bookingCancelledResponse, HttpStatus.OK);
    }

    @PatchMapping("/booking/change/{bookingId}")
    public ResponseEntity<FlightBookingChangeResponse> changeBooking(@PathVariable Long bookingId,
                                                                     @RequestBody FlightBookingChangeRequest flightBookingChangeRequest) {
        log.info("Changed booking on {}", new Date());
        FlightBookingChangeResponse flightBookingChangeResponse =
                bookingService.changeBooking(bookingId, flightBookingChangeRequest);
        return new ResponseEntity<>(flightBookingChangeResponse, HttpStatus.OK);
    }
}
