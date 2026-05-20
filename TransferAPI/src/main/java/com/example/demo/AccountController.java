package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private TransferService transferService;

    @PostMapping()
    public ResponseEntity<String> transferMoney( @Valid @RequestBody  TransferRequest request) {

        transferService.transferMoney(
                request.getFromId(),
                request.getToId(),
                request.getAmount()
        );

        return ResponseEntity.ok("Transfer successful");
    }
}