package entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    private LocalDateTime transactionDate;
    private String transactionType;
    private double amount;
    private int status;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Atm sourceMachine;

    public Transaction(Account account, Double amount, String transactionType, Atm sourceMachine) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.status = 0;
        this.sourceMachine = sourceMachine;
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

    public Account getAccount() {
        return this.account;
    }

    public Atm getSourceMachine() {
        return this.sourceMachine;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType
                + ", amount=" + amount + ", accountNumber" + account.getAccountNumber() + "]";
    }

}