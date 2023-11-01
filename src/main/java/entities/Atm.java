package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atm")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "atm_id")
    private int id;

    @ManyToOne
    private Bank bank;

    private double balance;

    public Atm(int id, String name, Bank bank) {
        this.id = id;
        this.bank = bank;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bank getBank() {
        return this.bank;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Atm [id=" + id + ", bank=" + bank.getId() + "]";
    }

}