package com.sf.dao.impl;

import com.sf.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByAccountNumber(String accountNumber);
    List<Account> findByAccountNumberAndPin(String accountNumber, String pin);
    List<Account> findByAccountNumberAndNameAndPin(String accountNumber, String name, String pin);
}
