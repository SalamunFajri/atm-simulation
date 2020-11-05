package com.sf.dao.impl;

import com.sf.dao.IBank;
import com.sf.exception.ErrorCode;
import com.sf.exception.atmSimulationException;
import com.sf.model.Account;
import com.sf.util.UtilCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SqlDbBank implements IBank {

    @Autowired
    private AccountRepository repository;

    public SqlDbBank(AccountRepository repository) {
        this.setRepository(repository);
    }

    public void uploadFileLocal(String csvFile)  {
        UtilCsv utilCsv = new UtilCsv();
        try {
            Stream<String> stream = Files.lines(Paths.get(csvFile)).skip(1);
            stream.map(utilCsv::parseLine).forEach(line -> {
                try {
                    this.add(new Account(line.get(0), line.get(1), Long.parseLong(line.get(2)), line.get(3)));
                } catch (atmSimulationException e) {
                    String errorCode = e.getMessage();
                    if (errorCode.contains("duplicated records")) {
                        System.out.println(ErrorCode.RECORD_DUPLICATE.getMessage()
                                .replace("{{record}}",
                                        line.get(0) + "," + line.get(1) + "," + line.get(3)));
                    } else {
                        System.out.println(ErrorCode.ACCOUNT__NUMBER_DUPLICATE.getMessage()
                                .replace("{{number}}", line.get(3)));
                    }

                }
            });
        } catch (IOException e) {
            System.out.println(ErrorCode.FILE_CSV_NOT_FOUND.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(ErrorCode.FILE_CSV_NOT_FOUND.getMessage());
        } finally {
            this.addDefaultAccount();
        }
    }

    @Override
    public void addDefaultAccount() {
        try {
            this.add(new Account("John Doe","012108", 100, "112233"));
            this.add(new Account("Jane Doe","932012", 30, "112244"));
        } catch (atmSimulationException e) {
            String errorCode = e.getMessage();
            if (errorCode.contains("duplicated records")) {
                System.out.println(ErrorCode.RECORD_DUPLICATE.getMessage());
            } else {
                System.out.println(ErrorCode.ACCOUNT__NUMBER_DUPLICATE.getMessage());
            }

        }
    }

    @Override
    public void add(Account account) throws atmSimulationException {
        if (getAccountByAllProperty(account.getAccountNumber(), account.getName(), account.getPin()) != null) {
            throw new atmSimulationException(
                    ErrorCode.RECORD_DUPLICATE.getMessage());
        } else if (getAccountByAccountNumber(account.getAccountNumber()) != null) {
            throw new atmSimulationException(
                    ErrorCode.ACCOUNT__NUMBER_DUPLICATE.getMessage());
        } else {
            getRepository().save(account);
        }
    }

    @Override
    public Account userLogin(String accountNumber, String pin) throws atmSimulationException {
        Account account = (Account) getRepository().findByAccountNumberAndPin(accountNumber, pin);
        if (account == null) {
            throw new atmSimulationException("Authentication Failed");
        } else return account;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        List<Account> accounts = getRepository().findByAccountNumber(accountNumber);
        if (accounts.size()>0) {
            return accounts.get(0);
        } else return null;
    }

    @Override
    public Account getAccountByAllProperty(String accountNumber, String name, String pin) {
        List<Account> accounts = getRepository().findByAccountNumberAndNameAndPin(accountNumber, name, pin);
        if (accounts.size()>0) {
            return accounts.get(0);
        } else return null;
    }

    public List<Account> getAll() {
        return (List<Account>) getRepository().findAll();
    }

    public AccountRepository getRepository() {
        return repository;
    }

    public void setRepository(AccountRepository repository) {
        this.repository = repository;
    }
}
