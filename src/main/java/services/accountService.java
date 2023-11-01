package services;

import java.util.List;
import dao.AccountRepository;
import dao.TransactionRepository;
import entities.Account;
import entities.Transaction;
import exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Method to get all transactions for a given account
    public List<Transaction> getAllTransactionsForAccount(Account account) {
        return transactionRepository.findByAccountOrderByTransactionDateAsc(account);
    }

    // get account by id
    public Account getAccountById(int id) {
        Account account = this.accountRepository.findById(id);
        return account;
    }

    // creating new account
    public Account createAccount(Account account) {
        Account result = accountRepository.save(account);
        return result;
    }

    // update atm pin
    public void updateAtmPin(int accountId, String pin) {
        // Retrieve the account by its ID
        Account account = this.accountRepository.findById(accountId);

        if (account != null) {
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
    public boolean verifyTransaction(Transaction transaction, int accountId) {
        // Retrieve the account by its ID
        Account account = this.accountRepository.findById(accountId);

        if (account != null) {
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
        return this.accountRepository.findByAccountNumber(AccountNumber);
    }

    // get pending transactions
    // public 

}
