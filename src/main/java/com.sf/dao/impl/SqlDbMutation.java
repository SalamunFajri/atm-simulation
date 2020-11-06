package com.sf.dao.impl;

import com.sf.dao.IMutation;
import com.sf.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SqlDbMutation implements IMutation {

    private List<Transaction> transactions = new ArrayList<>();

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public void add(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(long id) {
        Optional<Transaction> val = repository.findById(id);
        if (val.isPresent()) {
            return val.get();
        }
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
        List<Transaction> lst = repository.findByAccountNumber(accountNumber);
        return lst.stream()
                .limit(n)
                .sorted(Comparator.comparing(Transaction::getTimestamp)
                        .reversed())
                .collect(Collectors.toList());
    }
}
