package com.indianairlines.management.system.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportCreateRequest {

    private String name;

    private String city;

    private String country;

    private String airportCode;
}
