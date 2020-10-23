package com.sf.service;

import com.sf.model.Account;
import com.sf.dao.IMutation;

public interface ITransactionService {
    void withdraw(Account account, long amount);

    void fundTransfer(Account origAccount, Account destAccount, long amount);

    void printTransactionScreen(String accountNumber);

    IMutation getMutation();

    void setMutation(IMutation mutation);
}
