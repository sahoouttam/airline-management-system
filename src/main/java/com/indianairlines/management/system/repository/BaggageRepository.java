package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}
