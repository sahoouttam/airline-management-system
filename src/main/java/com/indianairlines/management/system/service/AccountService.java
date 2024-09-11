package com.indianairlines.management.system.service;

import com.indianairlines.management.system.data.entities.Account;
import com.indianairlines.management.system.data.entities.Passenger;
import com.indianairlines.management.system.exception.AccountNotFoundException;
import com.indianairlines.management.system.exception.InsufficientBalanceException;
import com.indianairlines.management.system.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void debitAmount(Passenger passenger, double amount) {
        log.info("Passenger with id {} debited by {}", passenger.getId(), amount);
        Account account = getAccountByPassenger(passenger);
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for booking");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Transactional
    public void creditAmount(Passenger passenger, double amount) {
        Account account = getAccountByPassenger(passenger);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    private Account getAccountByPassenger(Passenger passenger) {
        return accountRepository.findByPassenger(passenger)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
}
