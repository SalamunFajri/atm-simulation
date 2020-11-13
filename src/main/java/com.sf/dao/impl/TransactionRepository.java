package com.sf.dao.impl;

import com.sf.model.Account;
import com.sf.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccountNumber(String accountNumber);
    Optional<Transaction> findById(long id);
}
