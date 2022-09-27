package com.example.app.junit.junitspringboot.repositories;

import java.util.List;

import com.example.app.junit.junitspringboot.models.Account;

public interface AccountRepository {
    List<Account> findAll();
    Account findById(Long id);
    void update(Account account);

}
