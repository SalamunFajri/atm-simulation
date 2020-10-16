package com.sf.service;

import com.sf.model.Account;

public class TransactionService {

    public static void withdraw(Account account, long amount) {
        account.setBalance(account.getBalance()-amount);
    }

    public static void fundTransfer(Account origAccount, Account destAccount, long amount) {
        origAccount.setBalance(origAccount.getBalance()-amount);
        destAccount.setBalance(destAccount.getBalance()+amount);
    }

}
