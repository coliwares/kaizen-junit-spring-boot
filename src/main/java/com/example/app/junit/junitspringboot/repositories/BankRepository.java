package com.example.app.junit.junitspringboot.repositories;

import java.util.List;

import com.example.app.junit.junitspringboot.models.Bank;

public interface BankRepository {
    List<Bank> findAll();
    Bank findById(Long id);
    void update(Bank bank);
}
