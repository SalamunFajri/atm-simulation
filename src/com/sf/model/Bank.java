package com.sf.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    public List<Account> accounts = new ArrayList<>();

    public Bank() {

    }

    public void Add(Account account){
        accounts.add(account);
    }

    public Account userLogin(String accountNumber, String pin) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber)
                        && a.getPin().equals(pin))
                .findFirst().orElse(null);
    }

    public Account getAccount(String accountNumber) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().compareTo(accountNumber) == 0)
                .findFirst().orElse(null);
    }

}
