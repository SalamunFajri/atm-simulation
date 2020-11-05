package com.sf.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pin")
    private String pin;
    @Column(name = "balance")
    private long balance;
    @Column(name = "accountNumber")
    private String accountNumber;

    public Account(){
    }

    public Account(Long id, String name, String pin, long balance, String accountNumber) {
        this.setId(id);
        this.setName(name);
        this.setPin(pin);
        this.setBalance(balance);
        this.setAccountNumber(accountNumber);
    }

    public Account(String name, String pin, long balance, String accountNumber) {
        this.setName(name);
        this.setPin(pin);
        this.setBalance(balance);
        this.setAccountNumber(accountNumber);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

