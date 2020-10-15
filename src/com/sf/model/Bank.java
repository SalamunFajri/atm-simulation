package com.sf.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    public List<Account> accounts = new ArrayList<>();

    public Bank() {
        accounts.add(new Account("John Doe","012108", 100, "112233"));
        accounts.add(new Account("Jane Doe","932012", 30, "112244"));
    }

    public Account userLogin(String accountNumber, String pin) {
        for(Account a : this.accounts){
            if(a.getAccountNumber().compareTo(accountNumber)== 0
                    && a.getPin().compareTo(pin)== 0){
                return a;
            }
        }
        return null;
    }

    public Account getAccount(String accountNumber) {
        for(Account a : this.accounts){
            if(a.getAccountNumber().compareTo(accountNumber)== 0){
                return a;
            }
        }
        return null;
    }

}
