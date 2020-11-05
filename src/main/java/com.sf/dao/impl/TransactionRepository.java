package com.sf.dao.impl;

import com.sf.model.Account;
import com.sf.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
