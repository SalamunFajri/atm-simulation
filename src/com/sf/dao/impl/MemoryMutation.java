package com.sf.dao.impl;

import com.sf.dao.IMutation;
import com.sf.model.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryMutation implements IMutation {

    private List<Transaction> transactions = new ArrayList<>();

    public MemoryMutation() {

    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void Add(Transaction transaction){
        this.transactions.add(transaction);
    }

    @Override
    public Transaction getTransactionById(long id) {
        return this.transactions.stream()
                .filter(a -> a.getId()==id)
                .findFirst().orElse(null);
    }

    @Override
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public long getNextId(){
        return this.transactions.size() + 1;
    }

    @Override
    public List<Transaction> getLastNTransaction(String accountNumber, int n) {
        return this.transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .limit(n)
                .sorted(Comparator.comparing(Transaction::getTimestamp)
                        .reversed())
                .collect(Collectors.toList());
    }



}
