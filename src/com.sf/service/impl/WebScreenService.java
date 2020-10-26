package com.sf.service.impl;

import com.sf.dao.IBank;
import com.sf.model.Account;
import com.sf.service.IScreenService;
import com.sf.service.ITransactionService;

public class WebScreenService implements IScreenService {
    @Override
    public void run() {

    }

    @Override
    public void welcomeScreen() {

    }

    @Override
    public void transactionScreen() {

    }

    @Override
    public void printTransactionScreen() {

    }

    @Override
    public void ChooseTransactionOrWelcomeScreen() {

    }

    @Override
    public void withdrawScreen1() {

    }

    @Override
    public void withdrawScreen2() {

    }

    @Override
    public void withdrawSummaryScreen(long amountWithdraw) {

    }

    @Override
    public void fundTransferScreen1() {

    }

    @Override
    public void fundTransferScreen2(String destAccountNumb) {

    }

    @Override
    public void fundTransferScreen3(String destAccountNumb, long amountTransfer) {

    }

    @Override
    public void fundTransferScreen4(String destAccountNumb, long amountTransfer, String refNumb) {

    }

    @Override
    public void fundTransferSummaryScreen(String destAccountNumb, long amountTransfer, String refNumb) {

    }

    @Override
    public IBank getBank() {
        return null;
    }

    @Override
    public void setBank(IBank bank) {

    }

    @Override
    public Account getAuthAccount() {
        return null;
    }

    @Override
    public void setAuthAccount(Account authAccount) {

    }

    @Override
    public ITransactionService getTransactionService() {
        return null;
    }

    @Override
    public void setTransactionService(ITransactionService transactionService) {

    }

    @Override
    public void setAuto(boolean b) {

    }
}
