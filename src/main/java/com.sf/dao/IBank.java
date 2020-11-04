package com.sf.dao;

import com.sf.exception.atmSimulationException;
import com.sf.model.Account;

public interface IBank {
    void addDefaultAccount() throws atmSimulationException;

    void add(Account account) throws atmSimulationException;

    Account userLogin(String accountNumber, String pin) throws atmSimulationException;

    Account getAccountByAccountNumber(String accountNumber);

    Account getAccountByAllProperty(String accountNumber, String name, String pin);
}
