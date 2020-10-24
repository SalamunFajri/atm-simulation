package com.sf.service;

import com.sf.model.Account;
import com.sf.dao.IBank;

public interface IScreenService {
    void run();

    void welcomeScreen();

    void transactionScreen();

    IBank getBank();

    void setBank(IBank bank);

    Account getAuthAccount();

    void setAuthAccount(Account authAccount);

    ITransactionService getTransactionService();

    void setTransactionService(ITransactionService transactionService);
}
