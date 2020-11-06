package com.sf.service.impl;

import com.sf.exception.atmSimulationException;
import com.sf.model.Transaction;
import com.sf.service.IScreenService;
import com.sf.util.UtilCls;
import com.sf.util.UtilDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsoleMainMenuScrSvc {

    private IScreenService consoleScrSvc;

    public ConsoleMainMenuScrSvc(IScreenService consoleScrSvc)  {
        this.setConsoleScrSvc(consoleScrSvc);
    }

    public void run() {
        try {
            this.welcomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void welcomeScreen()  {
        String accountNumber;
        String pin;
        consoleScrSvc.getInp().RefreshDevice();
        consoleScrSvc.setAuthAccount(null);
        System.out.println();
        System.out.println(">>>>>>>>>>>>>>>  ATM SIMULATION  <<<<<<<<<<<<<<<<<");
        do {
            System.out.print("Enter Account Number: ");
            accountNumber = consoleScrSvc.getInp().getAccountNumber();
            System.out.print("Enter PIN: ");
            pin = consoleScrSvc.getInp().getPin();

            if (accountNumber.length() != 6) {
                System.out.println("Account Number should have 6 digits");
                continue;
            }
            if (!UtilCls.isNumeric(accountNumber)) {
                System.out.println("Account Number should only contains numbers");
                continue;
            }
            if (pin.length() != 6) {
                System.out.println("PIN should have 6 digits");
                continue;
            }
            if (!UtilCls.isNumeric(pin)) {
                System.out.println("PIN should only contains numbers");
                continue;
            }

            try {
                this.consoleScrSvc.setAuthAccount(consoleScrSvc.getTransactionService().getBank().userLogin(accountNumber, pin));
            } catch (atmSimulationException e) {
                    System.out.println("Invalid Account Number/PIN");
                    continue;
            }
        } while (consoleScrSvc.getAuthAccount() == null);
        if (consoleScrSvc.getIsAuto()) transactionScreen();
    }

    public  void transactionScreen()  {
        int choice = transactionMenuScreen();

        switch(choice) {
            case 1:
                ConsoleWidrawScrSvc consoleWidrawScrSvc = new ConsoleWidrawScrSvc(consoleScrSvc);
                consoleWidrawScrSvc.withdrawScreen1();
                break;
            case 2:
                ConsoleFundTrfScrSvc consoleFundTrfScrSvc = new ConsoleFundTrfScrSvc(consoleScrSvc);
                consoleFundTrfScrSvc.fundTransferScreen1();
                break;
            case 3:
                printTransactionScreen();
                break;
            case 4:
                welcomeScreen();
                break;

        }
    }

    private int transactionMenuScreen() {
        int choice;

        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Print Transaction");
            System.out.println("4. Exit");
            System.out.println("Please choose option[4]:");
            choice = consoleScrSvc.getInp().getTransactionChoice();
        } while(choice <1 || choice >4);
        return choice;
    }


    public void printTransactionScreen() {
        printTransactionScreen(consoleScrSvc.getAuthAccount().getAccountNumber());
        chooseTransactionOrWelcomeScreen();
    }

    public void printTransactionScreen(String accountNumber) {
        List<Transaction> list = consoleScrSvc.getTransactionService().getMutation().getLastNTransaction(accountNumber, 10);
        list.stream()
                .forEach(t->System.out.println(t.getAccountNumber() + ","
                        + UtilDate.asLocalDateTime(t.getTimestamp()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+","
                        +t.getTransactionType()+","
                        +t.getAmount()));
    }


    public void chooseTransactionOrWelcomeScreen() {
        int choice;
        do {
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.println("Choose option[2]:");
            choice = consoleScrSvc.getInp().getInt();
        } while (choice < 1 || choice > 2);

        switch (choice) {
            case 1:
                transactionScreen();
                break;
            case 2:
                welcomeScreen();
                break;
        }
    }


    public IScreenService getConsoleScrSvc() {
        return consoleScrSvc;
    }

    public void setConsoleScrSvc(IScreenService consoleScrSvc) {
        this.consoleScrSvc = consoleScrSvc;
    }
}
