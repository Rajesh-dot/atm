package io.atm.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.atm.demo.entities.Bank;
import io.atm.demo.exceptions.CustomException;
import io.atm.demo.services.BankService;

@RestController
public class bankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/createBank")
    public ResponseEntity<?> createBank(@RequestParam Bank bank) {
        try {
            Bank responseBank = bankService.createBank(bank);
            Map<String, Bank> response = new HashMap<>();
            response.put("bank", responseBank);
            // Return the response with the token
            return ResponseEntity.ok(response);

        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // @GetMapping("/openAccount")
    // public ResponseEntity<?> openAccount(@RequestParam bankId)
}
