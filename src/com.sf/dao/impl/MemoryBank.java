package com.sf.dao.impl;

import com.sf.dao.IBank;
import com.sf.exception.ErrorCode;
import com.sf.exception.atmSimulationException;
import com.sf.model.Account;
import com.sf.util.UtilCsv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class MemoryBank implements IBank {

    private List<Account> accounts = new ArrayList<>();

    public MemoryBank() {
        this.addDefaultAccount();
    }

    @Override
    public void addDefaultAccount()  {
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

    public MemoryBank(String csvFile) {
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
    public void add(Account account) throws atmSimulationException {
        if (getAccountByAllProperty(account.getAccountNumber(), account.getName(), account.getPin()) != null) {
            throw new atmSimulationException(
                    ErrorCode.RECORD_DUPLICATE.getMessage());
        } else if (getAccountByAccountNumber(account.getAccountNumber()) != null) {
            throw new atmSimulationException(
                    ErrorCode.ACCOUNT__NUMBER_DUPLICATE.getMessage());
        } else {
            accounts.add(account);
        }
    }


    @Override
    public Account userLogin(String accountNumber, String pin) throws atmSimulationException {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber)
                        && a.getPin().equals(pin))
                .findFirst().orElseThrow(() -> new atmSimulationException("Authentication Failed"));
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().compareTo(accountNumber) == 0)
                .findFirst().orElse(null);
    }

    @Override
    public Account getAccountByAllProperty(String accountNumber, String name, String pin) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().compareTo(accountNumber) == 0
                && a.getName().compareTo(name) == 0
                && a.getPin().compareTo(pin) == 0)
                .findFirst().orElse(null);
    }

}
