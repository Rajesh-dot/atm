package services;

import dao.TransactionRepository;
import entities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // get transaction by id
    public Transaction getTransactionById(int id) {
        Transaction transaction = this.transactionRepository.findById(id);
        return transaction;
    }

    // creating new transction
    public Transaction createTransaction(Transaction transaction) {
        Transaction result = transactionRepository.save(transaction);
        return result;
    }

}
