package com.indianairlines.management.system.repository;

import com.indianairlines.management.system.data.entities.Account;
import com.indianairlines.management.system.data.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByPassengerAndAccountNumber(Passenger passenger, String accountNumber);

}
