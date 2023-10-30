package entities;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    private Account account;

    private LocalDateTime transactionDate;
    private String transactionType;
    private double amount;
    private int status;

    @ManyToOne
    private Account sourceAccount;

    @ManyToOne
    private Account targetAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private String author;

    public Transaction(Account account, Double amount, String transactionType) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.status = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getStatus() {
        return this.status;
    }

    public void updateStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType
                + ", amount=" + amount + ", accountNumber" + account.getAccountNumber() + "]";
    }

}