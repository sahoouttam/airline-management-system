package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    Optional<List<CrewMember>> findByIdIn(List<Long> ids);
}
