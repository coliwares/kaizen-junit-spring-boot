package com.example.app.junit.junitspringboot.services;

import java.math.BigDecimal;

import com.example.app.junit.junitspringboot.models.Account;

public interface AccountService {
    Account findById(Long id);
    int checkTotalTransfers(Long bankId);
    BigDecimal checkBalance(Long accountId);
    void bankTransfer(Long OriginAccountNumber, Long targetAccountNumber, BigDecimal amount);

}
