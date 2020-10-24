package com.sf.service;

import com.sf.dao.IBank;
import com.sf.model.Account;

public interface IScreenService {
    void run();

    void welcomeScreen();

    void transactionScreen();

    void printTransactionScreen();

    void ChooseTransactionOrWelcomeScreen();

    void withdrawScreen1();

    void withdrawScreen2();

    void withdrawSummaryScreen(long amountWithdraw);

    void fundTransferScreen1();

    void fundTransferScreen2(String destAccountNumb);

    void fundTransferScreen3(String destAccountNumb, long amountTransfer);

    void fundTransferScreen4(String destAccountNumb, long amountTransfer, String refNumb);

    void fundTransferSummaryScreen(String destAccountNumb, long amountTransfer, String refNumb);

    IBank getBank();

    void setBank(IBank bank);

    Account getAuthAccount();

    void setAuthAccount(Account authAccount);

    ITransactionService getTransactionService();

    void setTransactionService(ITransactionService transactionService);

    void setAuto(boolean b);
}
