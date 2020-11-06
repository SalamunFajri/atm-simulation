package com.sf.service;

import com.sf.dao.IBank;
import com.sf.model.Account;
import com.sf.dao.IMutation;

public interface ITransactionService {
    void withdrawFrom(Account account, long amount);

    void fundTransfer(Account origAccount, Account destAccount, long amount);

    IBank getBank();

    void setBank(IBank bank);

    IMutation getMutation();

    void setMutation(IMutation mutation);
}
