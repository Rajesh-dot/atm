package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Account;
import entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Transaction findById(int id);

    List<Transaction> findByStatusAndOrderByTransactionDateAsc(int status);

    List<Transaction> findByAccountOrderByTransactionDateAsc(Account account);

    List<Transaction> findByAccountAndStatusOrderByTransactionDateAsc(Account account, int status);

}