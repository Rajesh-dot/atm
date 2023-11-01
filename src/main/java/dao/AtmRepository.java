package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Atm;

public interface AtmRepository extends JpaRepository<Atm, Long> {
    public Atm findById(int id);
}