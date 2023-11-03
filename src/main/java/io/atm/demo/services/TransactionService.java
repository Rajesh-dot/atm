package io.atm.demo.services;

import io.atm.demo.dao.TransactionRepository;
import io.atm.demo.entities.Transaction;
import io.atm.demo.exceptions.CustomException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // get transaction by id
    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            return transaction;
        } else {
            throw new CustomException("Invalid transactionId");
        }
    }

    // creating new transction
    public Transaction createTransaction(Transaction transaction) {
        Transaction result = transactionRepository.save(transaction);
        return result;
    }

}
