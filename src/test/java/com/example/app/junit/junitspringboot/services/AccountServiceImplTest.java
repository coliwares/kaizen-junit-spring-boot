package com.example.app.junit.junitspringboot.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.app.junit.junitspringboot.data.Data;
import com.example.app.junit.junitspringboot.repositories.AccountRepository;
import com.example.app.junit.junitspringboot.repositories.BankRepository;

@SpringBootTest
public class AccountServiceImplTest {

    AccountRepository accountRepository;
    BankRepository bankRepository;
    AccountService accountService;

    @BeforeEach
    void setUp(){
        accountRepository =  mock(AccountRepository.class);
        bankRepository = mock(BankRepository.class);
        accountService = new AccountServiceImpl(accountRepository, bankRepository);
    }

    @Test
    @DisplayName("given an account when check balance then return balance")
    void kata1(){
        when(accountRepository.findById(1L)).thenReturn(Data.ACCOUNT_001);
        BigDecimal balance = accountService.checkBalance(1L);
        assertEquals("1000", balance.toPlainString());        
    } 
    
}
