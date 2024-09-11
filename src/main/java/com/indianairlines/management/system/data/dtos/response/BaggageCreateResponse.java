package com.indianairlines.management.system.data.dtos.response;

import com.indianairlines.management.system.data.enums.BaggageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggageCreateResponse {

    private BaggageType baggageType;

    private Double weight;

    private String tagCode;
}
