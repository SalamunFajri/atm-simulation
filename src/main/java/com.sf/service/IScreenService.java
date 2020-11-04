package com.sf.service;

import com.sf.dao.IBank;
import com.sf.input.IInput;
import com.sf.model.Account;
import com.sf.service.ITransactionService;

public interface IScreenService  {

    IBank getBank();

    void setBank(IBank bank);

    Account getAuthAccount();

    void setAuthAccount(Account authAccount);

    ITransactionService getTransactionService();

    void setTransactionService(ITransactionService transactionService);

    boolean getIsAuto();

    void setIsAuto(boolean auto);

    IInput getInp();

    void setInp(IInput inp);

}
