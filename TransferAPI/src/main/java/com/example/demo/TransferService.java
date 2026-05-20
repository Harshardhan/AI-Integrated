package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    public void transferMoney(Long fromId, Long toId, double amount) {

        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Withdraw from sender
        from.setBalance(from.getBalance() - amount);
        accountRepository.save(from);

        // Simulate system failure
      //  if (true) {
          //  throw new RuntimeException("Deposit service failure");
       // }

        // Deposit to receiver
        to.setBalance(to.getBalance() + amount);
        accountRepository.save(to);
    }
}