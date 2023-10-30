package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
    public Bank findById(int id);
}