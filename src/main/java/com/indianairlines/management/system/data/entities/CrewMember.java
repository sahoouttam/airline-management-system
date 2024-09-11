package com.indianairlines.management.system.data.entities;

import com.indianairlines.management.system.data.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrewMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private Integer yearOfService;

    @ManyToMany(mappedBy = "crewMembers")
    private List<Flight> flights;


}
