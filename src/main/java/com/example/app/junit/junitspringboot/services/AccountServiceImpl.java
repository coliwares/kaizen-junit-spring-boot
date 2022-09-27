package com.example.app.junit.junitspringboot.services;

import java.math.BigDecimal;

import com.example.app.junit.junitspringboot.models.Account;
import com.example.app.junit.junitspringboot.models.Bank;
import com.example.app.junit.junitspringboot.repositories.AccountRepository;
import com.example.app.junit.junitspringboot.repositories.BankRepository;

public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private BankRepository bankRepository;

    

    public AccountServiceImpl(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public Account findById(Long id) {
        
        return accountRepository.findById(id);
    }

    @Override
    public int checkTotalTransfers(Long bankId) {
        Bank bank = bankRepository.findById(bankId);
        return bank.getTransferQty();
    }

    @Override
    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId);
        return account.getBalance();
    }

    @Override
    public void bankTransfer(Long originAccountNumber, Long targetAccountNumber, BigDecimal amount, Long bankId) {
        Bank bank = bankRepository.findById(bankId);
        int transferQty = bank.getTransferQty();
        bank.setTransferQty(++transferQty);
        bankRepository.update(bank);

        Account originAccount = accountRepository.findById(originAccountNumber);
        Account targetAccount = accountRepository.findById(targetAccountNumber);

        originAccount.withdraw(amount);
        accountRepository.update(originAccount);

        targetAccount.deposit(amount);
        accountRepository.update(targetAccount);
        
    }
    
}
