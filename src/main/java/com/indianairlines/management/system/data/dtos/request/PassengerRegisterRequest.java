package com.indianairlines.management.system.data.dtos.request;

import com.indianairlines.management.system.data.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerRegisterRequest {

    private String name;

    private Gender gender;

    private String email;

    private String address;

    private String phoneNumber;
}
