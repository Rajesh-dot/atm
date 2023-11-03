package io.atm.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "atm")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "atm_id")
    private Long id;

    @ManyToOne
    private Bank bank;

    private double balance;

    public Atm(String name, Bank bank) {
        this.bank = bank;
        this.balance = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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