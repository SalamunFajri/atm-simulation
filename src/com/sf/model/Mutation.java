package com.sf.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Mutation {

    private List<Transaction> transactions = new ArrayList<>();

    public Mutation() {

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void Add(Transaction transaction){
        this.transactions.add(transaction);
    }

    public Transaction getTransactionById(long id) {
        return this.transactions.stream()
                .filter(a -> a.getId()==id)
                .findFirst().orElse(null);
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getNextId(){
        return this.transactions.size() + 1;
    }

    public List<Transaction> getLastNTransaction(String accountNumber, int n) {
        return this.transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .limit(n)
                .sorted(Comparator.comparing(Transaction::getTimestamp)
                        .reversed())
                .collect(Collectors.toList());
    }



}
