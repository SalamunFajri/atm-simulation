package com.sf.dao.impl;

import com.sf.dao.IBank;
import com.sf.exception.atmSimulationException;
import com.sf.model.Account;

public class SqlDbBank implements IBank {
    public SqlDbBank(String fileCsv) {
    }

    @Override
    public void addDefaultAccount() throws atmSimulationException {

    }

    @Override
    public void add(Account account) throws atmSimulationException {

    }

    @Override
    public Account userLogin(String accountNumber, String pin) {
        return null;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public Account getAccountByAllProperty(String accountNumber, String name, String pin) {
        return null;
    }
}
