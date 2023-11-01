package services;

import dao.BankRepository;
import entities.Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bankService {

    @Autowired
    private BankRepository bankRepository;

    // get transaction by id
    public Bank getBankById(int id) {
        Bank bank = this.bankRepository.findById(id);
        return bank;
    }

    // creating new bank
    public Bank createBank(Bank bank) {
        Bank result = bankRepository.save(bank);
        return result;
    }

}
