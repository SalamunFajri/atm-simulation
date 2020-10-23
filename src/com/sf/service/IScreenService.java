package com.sf.service;

import com.sf.model.Account;
import com.sf.dao.IBank;

import java.util.Scanner;

public interface IScreenService {
    void Run();

    void welcomeScreen();

    void transactionScreen();

    IBank getBank();

    void setBank(IBank bank);

    Scanner getSc();

    void setSc(Scanner sc);

    Account getAuthAccount();

    void setAuthAccount(Account authAccount);

    ITransactionService getTransactionService();

    void setTransactionService(ITransactionService transactionService);
}
