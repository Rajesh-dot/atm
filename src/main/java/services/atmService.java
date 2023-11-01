package services;

import dao.AtmRepository;
import entities.Atm;
import entities.Bank;
import entities.User;
import exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class atmService {

    @Autowired
    private AtmRepository atmRepository;

    // get transaction by id
    public Atm getBankById(int id) {
        Atm atm = this.atmRepository.findById(id);
        return atm;
    }

    // creating new bank
    public Atm createAtm(Atm atm, User user, Bank bank) {
        if(bank.isBankAdmin(user)) {
            Atm result = atmRepository.save(atm);
            return result;
        } else {
            throw new CustomException("Didn't have permission to create user");
        }
    }

}
