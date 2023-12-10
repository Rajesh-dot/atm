package io.atm.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.atm.demo.entities.Account;
import io.atm.demo.entities.Atm;
import io.atm.demo.entities.Transaction;
import io.atm.demo.entities.User;
import io.atm.demo.exceptions.CustomException;
import io.atm.demo.services.AccountService;
import io.atm.demo.services.AtmService;
import io.atm.demo.services.TransactionService;
import io.atm.demo.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class accountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AtmService atmService;

    @GetMapping("/changeAtmPin")
    public ResponseEntity<?> changeAtmPin(@RequestParam String accountNo, @RequestParam String newPassword,
            @RequestParam String authToken) {
        try {
            User user = userService.getUserByAuthToken(authToken);
            Account account = accountService.getAccountByAccountNumber(accountNo);
            if (account.hasAccess(user)) {
                accountService.updateAtmPin(account.getId(), newPassword);
                Map<String, String> response = new HashMap<>();
                response.put("status", "ok");
                return ResponseEntity.ok(response);
            } else {
                throw new CustomException("No Access to account");
            }
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/withDraw")
    public ResponseEntity<?> withDrawMoney(@RequestParam String accountNo, @RequestParam Double amount,
            @RequestParam String pin, @RequestParam String authToken, @RequestParam String machineKey) {
        try {
            User user = userService.getUserByAuthToken(authToken);
            Atm sourceMachine = atmService.getAtmByMachineKey(machineKey);
            Account account = accountService.getAccountByAccountNumber(accountNo);
            if (account.hasAccess(user)) {
                if (account.ValidateAtmPin(pin)) {
                    if (account.getBalance() >= amount) {
                        Transaction transaction = new Transaction(account, amount, "withDraw", sourceMachine);
                        transactionService.createTransaction(transaction);
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Balance");
                    }
                } else {
                    throw new CustomException("Invalid ATM pin");
                }
                Map<String, String> response = new HashMap<>();
                response.put("status", "ok");
                return ResponseEntity.ok(response);
            } else {
                throw new CustomException("No Access to account");
            }
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
