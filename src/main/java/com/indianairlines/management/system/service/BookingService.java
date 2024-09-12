package com.indianairlines.management.system.service;

import com.indianairlines.management.system.conflig.ExecutorConfig;
import com.indianairlines.management.system.data.dtos.request.BookingCancelRequest;
import com.indianairlines.management.system.data.dtos.request.FlightBookingChangeRequest;
import com.indianairlines.management.system.data.dtos.request.FlightBookingRequest;
import com.indianairlines.management.system.data.dtos.response.BookingCancelledResponse;
import com.indianairlines.management.system.data.dtos.response.FlightBookingChangeResponse;
import com.indianairlines.management.system.data.dtos.response.FlightBookingResponse;
import com.indianairlines.management.system.data.entities.*;
import com.indianairlines.management.system.data.enums.BookingStatus;
import com.indianairlines.management.system.exception.BookingCancelledException;
import com.indianairlines.management.system.exception.BookingNotFoundException;
import com.indianairlines.management.system.exception.FlightNotFoundException;
import com.indianairlines.management.system.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final SeatService seatService;
    private final ExecutorConfig executorConfig;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          PaymentService paymentService,
                          PassengerService passengerService,
                          FlightService flightService,
                          SeatService seatService,
                          ExecutorConfig executorConfig) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.passengerService = passengerService;
        this.flightService = flightService;
        this.seatService = seatService;
        this.executorConfig = executorConfig;
    }

    @Transactional
    public CompletableFuture<FlightBookingResponse> confirmFlightBooking(FlightBookingRequest flightBookingRequest) {
        return CompletableFuture.supplyAsync(() -> confirmBooking(flightBookingRequest),
                executorConfig.getConfig());
    }

    public FlightBookingResponse confirmBooking(FlightBookingRequest flightBookingRequest) {
        Passenger passenger = passengerService.getPassengerById(
                flightBookingRequest.getPassengerId());
        Flight flight = findFlight(flightBookingRequest.getSource(),
                flightBookingRequest.getDestination(), flightBookingRequest.getFlightDate());
        Booking booking = Booking.builder()
                .totalPrice(flightBookingRequest.getBookingFee())
                .bookingTime(new Date())
                .bookingStatus(BookingStatus.CONFIRMED)
                .passenger(passenger)
                .flight(flight)
                .build();
        Seat seat = seatService.allocateSeat(flight, flightBookingRequest.getSeatType());
        Payment payment = paymentService.processPayment(booking,
                flightBookingRequest.getTransactionType(),
                flightBookingRequest.getBookingFee(),
                flightBookingRequest.getAccountNumber());
        Booking confirmedBooking = bookingRepository.save(booking);
        return new FlightBookingResponse(
                confirmedBooking.getId(),
                flight.getSource().getCity(),
                flight.getDestination().getCity(),
                seat.getSeatNumber(),
                seat.getSeatType().toString(),
                payment.getTransactionType().toString(),
                flightBookingRequest.getBookingFee());
    }

    @Transactional
    public BookingCancelledResponse cancelBooking(Long bookingId, BookingCancelRequest bookingCancelRequest) {
        Booking booking = getBookingById(bookingId);
        if (BookingStatus.CANCELLED.equals(booking.getBookingStatus())) {
            throw new BookingCancelledException("Booking already cancelled");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        Flight flight = booking.getFlight();
        Seat unallocatedSeat = seatService.unallocateSeat(flight, bookingCancelRequest.getSeatType(),
                bookingCancelRequest.getSeatNumber());

        Payment paymentRefund = paymentService.processRefund(booking, bookingCancelRequest.getAccountNumber());
        return new BookingCancelledResponse(bookingId,
                paymentRefund.getTransactionAmount(),
                unallocatedSeat.getSeatNumber());
    }

    @Transactional
    public FlightBookingChangeResponse changeBooking(Long bookingId, FlightBookingChangeRequest flightBookingChangeRequest) {
        cancelBooking(bookingId, new BookingCancelRequest(
                flightBookingChangeRequest.getSeatType(),
                flightBookingChangeRequest.getSeatNumber(),
                flightBookingChangeRequest.getAccountNumber()));
        FlightBookingResponse flightBookingResponse = confirmBooking(
                new FlightBookingRequest(flightBookingChangeRequest.getPassengerId(),
                        flightBookingChangeRequest.getNewSource(),
                        flightBookingChangeRequest.getNewDestination(),
                        flightBookingChangeRequest.getNewFlightDate(),
                        flightBookingChangeRequest.getSeatType(),
                        flightBookingChangeRequest.getBookingFee(),
                        flightBookingChangeRequest.getAccountNumber(),
                        flightBookingChangeRequest.getTransactionType()));
        return FlightBookingChangeResponse.builder()
                .bookingId(flightBookingResponse.getBookingId())
                .source(flightBookingResponse.getSource())
                .destination(flightBookingResponse.getDestination())
                .flightDate(flightBookingChangeRequest.getNewFlightDate())
                .seatNumber(flightBookingResponse.getSeatNumber())
                .build();
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(
                        "Booking not found with id " + bookingId));
    }

    private Flight findFlight(String source, String destination, Date flightDate) {
        Optional<List<Flight>> flights = flightService
                .getAllAirportBetweenSourceAndDestination(source, destination, flightDate);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flight available");
        }
        return flights.get().get(0);
    }
}
