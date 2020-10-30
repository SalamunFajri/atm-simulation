package com.sf.model;

import java.util.concurrent.atomic.AtomicReference;

public class Account {

    private String name;
    private String pin;
    private long balance;
    private String accountNumber;

    public Account(String name, String pin, long balance, String accountNumber) {
        this.setName(name);
        this.setPin(pin);
        this.setBalance(balance);
        this.setAccountNumber(accountNumber);
    }

    public Account(AtomicReference<String> name, AtomicReference<String> pin,
                   AtomicReference<Long> balance, AtomicReference<String> accountNumber) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

