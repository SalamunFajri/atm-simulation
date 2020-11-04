package com.sf.dao.impl;

import com.sf.dao.IMutation;
import com.sf.model.Transaction;

import java.util.List;

public class SqlDbMutation implements IMutation {
    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public void add(Transaction transaction) {

    }

    @Override
    public Transaction getTransactionById(long id) {
        return null;
    }

    @Override
    public void setTransactions(List<Transaction> transactions) {

    }

    @Override
    public long getNextId() {
        return 0;
    }

    @Override
    public List<Transaction> getLastNTransaction(String accountNumber, int n) {
        return null;
    }
}
