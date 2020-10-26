package com.sf.service.impl;

import com.sf.dao.IMutation;
import com.sf.dao.impl.SqlDbMutation;
import com.sf.model.Account;
import com.sf.service.ITransactionService;

public class SqlDbTransactionService implements ITransactionService {
    public SqlDbTransactionService(SqlDbMutation sqlDbMutation) {
    }

    @Override
    public void withdraw(Account account, long amount) {

    }

    @Override
    public void fundTransfer(Account origAccount, Account destAccount, long amount) {

    }

    @Override
    public void printTransactionScreen(String accountNumber) {

    }

    @Override
    public IMutation getMutation() {
        return null;
    }

    @Override
    public void setMutation(IMutation mutation) {

    }
}
