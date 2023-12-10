package io.atm.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.atm.demo.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findById(Long id);

    public Optional<Account> findByAccountNumber(String accountNumber);

    public Optional<Account> findByAtmNumber(String atmNumber);
}