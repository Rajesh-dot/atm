package io.atm.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.atm.demo.entities.Account;
import io.atm.demo.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Optional<Transaction> findById(Long id);

    public List<Transaction> findByStatusOrderByTransactionDateAsc(int status);

    public List<Transaction> findByAccountOrderByTransactionDateAsc(Account account);

    public List<Transaction> findByAccountAndStatusOrderByTransactionDateAsc(Account account, int status);

}