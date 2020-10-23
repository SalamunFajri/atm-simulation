package com.sf.dao;

import com.sf.exception.atmSimulationException;
import com.sf.model.Account;

public interface IBank {
    void AddDefaultAccount() throws atmSimulationException;

    void Add(Account account) throws atmSimulationException;

    Account userLogin(String accountNumber, String pin);

    Account getAccountByAccountNumber(String accountNumber);

    Account getAccountByAllProperty(String accountNumber, String name, String pin);
}
