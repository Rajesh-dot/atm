package io.atm.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateTransaction {
    @GetMapping("/validate")
    public boolean Validate(@RequestParam String transaction) {
        return true;
    }
}
