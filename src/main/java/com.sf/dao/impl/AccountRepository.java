package com.sf.dao.impl;

import com.sf.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByAccountNumberAndPin(String accountNumber, String pin);
    List<Account> findByAccountNumberAndNameAndPin(String accountNumber, String name, String pin);
    Optional<Account> findByAccountNumber(String accountNumber);
}
