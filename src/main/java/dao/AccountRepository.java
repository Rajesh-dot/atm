package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findById(int id);

    public Account findByAccountNumber(String accountNumber);
}