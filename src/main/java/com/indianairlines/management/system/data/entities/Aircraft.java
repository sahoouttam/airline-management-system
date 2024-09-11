package com.indianairlines.management.system.data.entities;


import com.indianairlines.management.system.data.enums.AircraftManufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AircraftManufacturer aircraftManufacturer;

    private String model;

    private String registrationNumber;

    private int seatCapacity;

}
