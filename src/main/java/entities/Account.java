package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int id;

    private String accountNumber;
    private String atmPin;
    private double balance;
    @ManyToOne
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private String author;

    public Account(String atmPin) {
        this.balance = 0;
        this.accountNumber = generateRandomAccountNumber();
        this.atmPin = atmPin;
    }

    public Account() {
        this.balance = 0;
        this.accountNumber = generateRandomAccountNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean ValidateAtmPin(String atmPin) {
        if (this.atmPin == null && atmPin == null) {
            return true;
        } else if (this.atmPin == null || atmPin == null) {
            return false;
        }
        return this.atmPin.equals(atmPin);
    }

    public boolean setAtmPin(String atmPin) {
        if (atmPin != null && atmPin.matches("\\d{4}")) {
            this.atmPin = atmPin;
            return true; // ATM pin set successfully
        } else {
            return false; // Invalid ATM pin format
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean applyTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        if (amount < 0) {
            if (this.balance >= -amount) {
                this.balance += amount;
                return true;
            }
            return false;
        } else {
            this.balance += amount;
            return true;
        }
    }

    public void revertTransaction(Transaction transaction) {
        double amount = -1 * transaction.getAmount();
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Bank [id=" + id + ", accountNumber=" + accountNumber + "]";
    }

    public static String generateRandomAccountNumber() {
        // Create a random number generator
        Random random = new Random();

        // Generate a random integer for the account number
        int accountNumberInt = random.nextInt(1000000); // You can adjust the range as needed

        // Convert the integer to a string
        String accountNumber = String.format("%06d", accountNumberInt);

        // Create a random UUID and append it to the account number (for uniqueness)
        String uniquePart = UUID.randomUUID().toString().replace("-", "").substring(0, 6);

        return accountNumber + uniquePart;
    }

}