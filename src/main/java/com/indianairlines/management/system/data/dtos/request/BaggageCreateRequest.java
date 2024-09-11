package com.indianairlines.management.system.data.dtos.request;

import com.indianairlines.management.system.data.enums.BaggageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggageCreateRequest {

    private BaggageType baggageType;

    private Double weight;

    private String tagCode;

    private String description;

    private Long passengerId;
}
