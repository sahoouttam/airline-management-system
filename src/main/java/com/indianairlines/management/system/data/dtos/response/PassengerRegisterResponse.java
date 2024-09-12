package com.indianairlines.management.system.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerRegisterResponse {

    private String name;

    private String phoneNumber;

    private String address;
}
