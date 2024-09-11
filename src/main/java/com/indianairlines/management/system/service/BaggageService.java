package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.dtos.request.BaggageCreateRequest;
import com.indianairlines.management.system.data.dtos.response.BaggageCreateResponse;
import com.indianairlines.management.system.data.entities.Baggage;
import com.indianairlines.management.system.data.entities.Booking;
import com.indianairlines.management.system.repository.BaggageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaggageService {

    private final BaggageRepository baggageRepository;
    private final BookingService bookingService;

    @Autowired
    public BaggageService(BaggageRepository baggageRepository,
                          BookingService bookingService) {
        this.baggageRepository = baggageRepository;
        this.bookingService = bookingService;
    }

    public BaggageCreateResponse createBaggage(BaggageCreateRequest baggageCreateRequest) {
        Booking booking = bookingService.getBookingById(baggageCreateRequest.getBookingId());
        Baggage baggageCreated = Baggage.builder()
                .baggageType(baggageCreateRequest.getBaggageType())
                .weight(baggageCreateRequest.getWeight())
                .tagCode(baggageCreateRequest.getTagCode())
                .description(baggageCreateRequest.getDescription())
                .booking(booking)
                .build();
        Baggage baggage = baggageRepository.save(baggageCreated);
        log.info("Baggage created against booking {}", baggageCreateRequest.getBookingId());
        return BaggageCreateResponse.builder()
                .baggageType(baggage.getBaggageType())
                .weight(baggage.getWeight())
                .tagCode(baggage.getTagCode())
                .build();
    }


}
