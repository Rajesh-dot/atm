package io.atm.demo.services;

import java.util.List;
import java.util.Optional;

import io.atm.demo.dao.AccountRepository;
import io.atm.demo.dao.TransactionRepository;
import io.atm.demo.entities.Account;
import io.atm.demo.entities.Bank;
import io.atm.demo.entities.Transaction;
import io.atm.demo.entities.User;
import io.atm.demo.exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Method to get all transactions for a given account
    public List<Transaction> getAllTransactionsForAccount(Account account) {
        return transactionRepository.findByAccountOrderByTransactionDateAsc(account);
    }

    // get account by id
    public Account getAccountById(Long id) {
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return account;
        } else {
            throw new CustomException("Invalid accountId");
        }
    }

    // creating new account
    public Account createAccount(Account account) {
        Account result = accountRepository.save(account);
        return result;
    }

    public Account createAccount(Bank bank, User user) {
        Account account = new Account(bank, user);
        Account result = accountRepository.save(account);
        return result;
    }

    // update atm pin
    public void updateAtmPin(Long accountId, String pin) {
        // Retrieve the account by its ID
        Optional<Account> accountOptional = this.accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            boolean result = account.setAtmPin(pin);
            if (result) {
                accountRepository.save(account);
            } else {
                throw new CustomException("Atm pin should be of 4 digits.");
            }
        } else {
            throw new CustomException("Account not found");
        }
    }

    // verify transaction
    public boolean verifyTransaction(Transaction transaction, Long accountId) {
        // Retrieve the account by its ID
        Optional<Account> accountOptional = this.accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            double amount = transaction.getAmount();
            if (amount < 0 && account.getBalance() >= -amount) {
                return false;
            } else {
                return true;
            }
        } else {
            throw new CustomException("Account not found");
        }
    }

    // apply transaction
    public void applyTransaction(Transaction transaction, Account account) {
        boolean result = account.applyTransaction(transaction);
        if (result) {
            accountRepository.save(account);
            transaction.updateStatus(2);
            this.transactionRepository.save(transaction);
        } else {
            transaction.updateStatus(-1);
            this.transactionRepository.save(transaction);
            throw new CustomException("Insufficient Balance.");
        }
    }

    // revert transaction
    public void revertTransaction(Transaction transaction, Account account) {
        account.revertTransaction(transaction);
        accountRepository.save(account);
        transaction.updateStatus(-2);
        this.transactionRepository.save(transaction);
    }

    public Account getAccountByAccountNumber(String AccountNumber) {
        Optional<Account> accountOptional = this.accountRepository.findByAccountNumber(AccountNumber);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return account;
        } else {
            throw new CustomException("Invalid AccountNumber");
        }
    }

    // get pending transactions
    // public

}
