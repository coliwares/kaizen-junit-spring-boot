package com.example.app.junit.junitspringboot.data;

import java.math.BigDecimal;

import com.example.app.junit.junitspringboot.models.Account;
import com.example.app.junit.junitspringboot.models.Bank;

public class Data {
    public static final Account ACCOUNT_001 = new Account(1L, "Cristian", new BigDecimal(1000));
    public static final Account ACCOUNT_002 = new Account(2L, "Marcelo", new BigDecimal(2000));
    public static final Bank BANK = new Bank(1L, "Bank of Square", 0);
    
}
