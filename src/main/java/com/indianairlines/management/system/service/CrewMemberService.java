package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.entities.CrewMember;
import com.indianairlines.management.system.repository.CrewMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    @Autowired
    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> getAllMemberByIds(List<Long> crewMemberIds) {
        return crewMemberRepository.findByIdIn(crewMemberIds)
                .orElseThrow(() -> new EntityNotFoundException("No crew member found"));
    }
}
