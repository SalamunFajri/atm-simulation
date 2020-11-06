package com.sf.service.impl;

import com.sf.input.IInput;
import com.sf.model.Account;
import com.sf.dao.IBank;
import com.sf.service.IScreenService;
import com.sf.service.ITransactionService;

public class ConsoleScrSvc implements IScreenService {

    private IInput inp;
    private Account authAccount = null;
    private ITransactionService transactionService;
    private boolean isAuto;

    public ConsoleScrSvc(ITransactionService transactionService, IInput inp)  {
        this.setTransactionService(transactionService);
        this.setInp(inp);
        this.isAuto = true;
    }

    @Override
    public Account getAuthAccount() {
        return authAccount;
    }

    @Override
    public void setAuthAccount(Account authAccount) {
        this.authAccount = authAccount;
    }

    @Override
    public ITransactionService getTransactionService() {
        return transactionService;
    }

    @Override
    public void setTransactionService(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public boolean getIsAuto() {
        return isAuto;
    }

    @Override
    public void setIsAuto(boolean auto) {
        isAuto = auto;
    }

    @Override
    public IInput getInp() {
        return inp;
    }

    @Override
    public void setInp(IInput inp) {
        this.inp = inp;
    }

}
