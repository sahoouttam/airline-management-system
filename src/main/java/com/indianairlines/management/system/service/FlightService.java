package com.indianairlines.management.system.service;

import com.indianairlines.management.system.conflig.ExecutorConfig;
import com.indianairlines.management.system.data.dtos.request.FlightAircraftAssignmentRequest;
import com.indianairlines.management.system.data.dtos.request.FlightCreateRequest;
import com.indianairlines.management.system.data.dtos.request.FlightSearchRequest;
import com.indianairlines.management.system.data.dtos.response.FlightAircraftAssignmentResponse;
import com.indianairlines.management.system.data.dtos.response.FlightCreateResponse;
import com.indianairlines.management.system.data.dtos.response.FlightSearchResponse;
import com.indianairlines.management.system.data.entities.*;
import com.indianairlines.management.system.data.enums.FlightStatus;
import com.indianairlines.management.system.exception.FlightNotFoundException;
import com.indianairlines.management.system.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final AircraftService aircraftService;
    private final CrewMemberService crewMemberService;

    @Autowired
    public FlightService(FlightRepository flightRepository,
                         AirportService airportService,
                         AircraftService aircraftService,
                         CrewMemberService crewMemberService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
        this.aircraftService = aircraftService;
        this.crewMemberService = crewMemberService;
    }

    public FlightCreateResponse createFlight(FlightCreateRequest flightCreateRequest) {
        Airport sourceAirport = airportService.findAirportById(flightCreateRequest.getSourceId());
        Airport destinationAirport = airportService.findAirportById(flightCreateRequest.getDestinationId());
        Aircraft aircraft = aircraftService.getById(flightCreateRequest.getAircraftId());
        Flight flight = Flight.builder()
                .aircraft(aircraft)
                .source(sourceAirport)
                .destination(destinationAirport)
                .sourceDepartureTime(flightCreateRequest.getDepartureTime())
                .destinationArrivalTime(flightCreateRequest.getArrivalTime())
                .flightStatus(FlightStatus.valueOf(flightCreateRequest.getFlightStatus()))
                .build();
        Flight createdFlight = saveFlight(flight);
        return new FlightCreateResponse(
                createdFlight.getId(),
                getAircraftName(aircraft),
                sourceAirport.getCity(),
                destinationAirport.getCity(),
                createdFlight.getSourceDepartureTime(),
                createdFlight.getDestinationArrivalTime()
        );
    }

    public List<FlightSearchResponse> searchFlightBetween(String source, String destination, Date flightDate) {
        FlightSearchRequest flightSearchRequest = FlightSearchRequest.builder()
                .sourceCity(source)
                .destinationCity(destination)
                .schedulingDate(flightDate)
                .build();
        return searchFlight(flightSearchRequest).stream()
                .map(this::convertFlightToFlightSearchResponse)
                .collect(Collectors.toList());
    }

    public List<Flight> searchFlight(FlightSearchRequest flightSearchRequest) {
        Optional<List<Flight>> flights = getAllAirportBetweenSourceAndDestination(
                flightSearchRequest.getSourceCity(),
                flightSearchRequest.getDestinationCity(),
                flightSearchRequest.getSchedulingDate());

        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights found");
        }

        return flights.get();
    }

    public Optional<List<Flight>> getAllAirportBetweenSourceAndDestination(String source, String destination, Date departureTime) {
        Pair<Airport, Airport> sourceDestinationPair =
                findSourceAndDestination(source, destination);
        Airport sourceAirport = sourceDestinationPair.getFirst();
        Airport destinationAirport = sourceDestinationPair.getSecond();
        return flightRepository.findBySourceAndDestinationAndSourceDepartureTime(
                sourceAirport, destinationAirport, departureTime);
    }

    public FlightAircraftAssignmentResponse assignAircraftAndCrewMembers(Long flightId,
                                                                         FlightAircraftAssignmentRequest flightAircraftAssignmentRequest) {
        Aircraft aircraft = aircraftService
                .getById(flightAircraftAssignmentRequest.getAircraftId());
        Flight flight = getFlightDetails(flightId,
                flightAircraftAssignmentRequest.getSource(),
                flightAircraftAssignmentRequest.getDestination(),
                flightAircraftAssignmentRequest.getFlightDate());
        List<CrewMember> crewMembers = crewMemberService
                .getAllMemberByIds(flightAircraftAssignmentRequest.getCrewMemberIds());
        flight.setAircraft(aircraft);
        flight.setCrewMembers(crewMembers);
        flight.setFlightStatus(FlightStatus.SCHEDULED);
        Flight assignedFlight = flightRepository.save(flight);
        return new FlightAircraftAssignmentResponse(
                aircraft.getModel(),
                assignedFlight.getFlightStatus(),
                assignedFlight.getCrewMembers().size(),
                aircraft.getSeatCapacity()
        );
    }

    private Flight getFlightDetails(Long flightId, String source, String destination, Date flightDate) {
        Pair<Airport, Airport> sourceDestinationPair =
                findSourceAndDestination(source, destination);
        Airport sourceAirport = sourceDestinationPair.getFirst();
        Airport destinationAirport = sourceDestinationPair.getSecond();

        return flightRepository.findByIdAndSourceAndDestinationAndSourceDepartureTime(
                        flightId, sourceAirport, destinationAirport, flightDate)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id " + flightId));
    }

    private Pair<Airport, Airport> findSourceAndDestination(String source, String destination) {
        Optional<List<Airport>> departureAirports = airportService
                .getAllAirportsByCity(source);
        Optional<List<Airport>> arrivalAirports = airportService
                .getAllAirportsByCity(destination);

        if (departureAirports.isEmpty() || arrivalAirports.isEmpty()) {
            throw new FlightNotFoundException("No flights found");
        }

        Airport sourceAirport = departureAirports.get().get(0);
        Airport destinationAirport = arrivalAirports.get().get(0);
        return Pair.of(sourceAirport, destinationAirport);
    }

    private FlightSearchResponse convertFlightToFlightSearchResponse(Flight flight) {
        Aircraft aircraft = flight.getAircraft();
        return FlightSearchResponse.builder()
                .departureTime(flight.getSourceDepartureTime())
                .arrivalTime(flight.getDestinationArrivalTime())
                .aircraftManufacturer(aircraft.getAircraftManufacturer())
                .model(aircraft.getModel())
                .seatCapacity(aircraft.getSeatCapacity())
                .build();
    }

    private Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    private String getAircraftName(Aircraft aircraft) {
        return aircraft.getAircraftManufacturer().toString() +
                " " + aircraft.getModel();
    }

}
