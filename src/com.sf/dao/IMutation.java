package com.sf.dao;

import com.sf.model.Transaction;

import java.util.List;

public interface IMutation {
    List<Transaction> getTransactions();

    void add(Transaction transaction);

    Transaction getTransactionById(long id);

    void setTransactions(List<Transaction> transactions);

    long getNextId();

    List<Transaction> getLastNTransaction(String accountNumber, int n);
}
