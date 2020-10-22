package com.sf.model;

import com.sf.exception.ErrorCode;
import com.sf.exception.atmSimulationException;
import com.sf.util.UtilCsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    public List<Account> accounts = new ArrayList<>();

    public Bank() {
    }

    public void AddDefaultAccount() throws atmSimulationException {
        this.Add(new Account("John Doe","012108", 100, "112233"));
        this.Add(new Account("Jane Doe","932012", 30, "112244"));
    }

    public Bank(String csvFile) throws atmSimulationException {
        Scanner scanner = null;
        String name = "";
        String pin = "";
        Long balance = 0L;
        String accountNumber = "";
        UtilCsv utilCsv = new UtilCsv();
        try {
            scanner = new Scanner(new File(csvFile));
            List<String> line = utilCsv.parseLine(scanner.nextLine());
            while (scanner.hasNext()) {
                line = utilCsv.parseLine(scanner.nextLine());
                name = line.get(0);
                pin = line.get(1);
                String balanceStr = line.get(2);
                balance = Long.parseLong(balanceStr);
                accountNumber = line.get(3);
                this.Add(new Account(name,pin,balance,accountNumber));
            }
        } catch (FileNotFoundException e) {
            throw new atmSimulationException(ErrorCode.FILE_CSV_NOT_FOUND.getMessage(), e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (atmSimulationException e) {
            String errorCode = e.getMessage();
            if (errorCode.contains("duplicated records")) {
                throw new atmSimulationException(
                        ErrorCode.RECORD_DUPLICATE.getMessage()
                                .replace("{{record}}",
                                        name+","+pin+","+ accountNumber));
            } else {
                throw new atmSimulationException(
                        ErrorCode.ACCOUNT__NUMBER_DUPLICATE.getMessage()
                                .replace("{{number}}", accountNumber));
            }
        }
        finally {
            if (scanner!=null) {
                scanner.close();
            }
        }
    }

    public void Add(Account account) throws atmSimulationException {
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


    public Account userLogin(String accountNumber, String pin) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber)
                        && a.getPin().equals(pin))
                .findFirst().orElse(null);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().compareTo(accountNumber) == 0)
                .findFirst().orElse(null);
    }

    public Account getAccountByAllProperty(String accountNumber, String name, String pin) {
        return this.accounts.stream()
                .filter(a -> a.getAccountNumber().compareTo(accountNumber) == 0
                && a.getName().compareTo(name) == 0
                && a.getPin().compareTo(pin) == 0)
                .findFirst().orElse(null);
    }

}
