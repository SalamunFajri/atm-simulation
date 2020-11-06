package com.sf.service.impl;

import com.sf.service.IScreenService;
import com.sf.util.UtilCls;

public class ConsoleFundTrfScrSvc  {

    private IScreenService consoleScrSvc;

    public ConsoleFundTrfScrSvc(IScreenService consoleScrSvc)  {
        this.setConsoleScrSvc(consoleScrSvc);
    }

    public void fundTransferScreen1() {
        String destAccountNumb;
        System.out.println("Please enter destination account and press enter to continue or");
        System.out.println("press enter to go back Transaction: ");
        destAccountNumb = consoleScrSvc.getInp().getDestAccountNumber();
        if (destAccountNumb.length()>0) {
            fundTransferScreen2(destAccountNumb);
        } else {
            ConsoleMainMenuScrSvc consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
            consoleMainMenuScrSvc.transactionScreen();
        }
    }

    public void fundTransferScreen2(String destAccountNumb) {
        long amountTransfer;
        do {
            System.out.println("Please enter transfer amount and press enter to continue or");
            System.out.println("press enter to go back to Transaction:");
            amountTransfer = consoleScrSvc.getInp().getAmount();
        } while (amountTransfer < 0);
        fundTransferScreen3(destAccountNumb, amountTransfer);
    }

    public void fundTransferScreen3(String destAccountNumb, long amountTransfer) {
        String keyIn;
        int refNumb = UtilCls.random6Digits();
        Integer refNumbInt = new Integer(refNumb);
        System.out.printf("Reference Number: %s%n", refNumbInt.toString());
        System.out.println("press enter to continue or press T to go back to Transaction: ");
        keyIn = consoleScrSvc.getInp().getString();
        if (keyIn.toUpperCase().equals("T")) {
            ConsoleMainMenuScrSvc consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
            consoleMainMenuScrSvc.transactionScreen();
        } else {
            fundTransferScreen4(destAccountNumb, amountTransfer, refNumbInt.toString());
        }
    }

    public void fundTransferScreen4(String destAccountNumb, long amountTransfer, String refNumb) {
        int choice;
        System.out.println("Transfer Confirmation");
        System.out.printf("Destination Account :%s%n", destAccountNumb);
        System.out.printf("Transfer Amount     :$%d%n", amountTransfer);
        System.out.printf("Reference Number    :%s%n", refNumb);
        System.out.println();
        do {
            System.out.println("1. Confirm Trx");
            System.out.println("2. Cancel Trx");
            System.out.print("Choose option[2]:");
            choice = consoleScrSvc.getInp().getConfirmChoice();
        }while(choice <1 || choice >2);

        if (destAccountNumb.length() != 6) {
            System.out.println("Account Number should have 6 digits");
            fundTransferScreen1();
        } else if (!UtilCls.isNumeric(destAccountNumb)) {
            System.out.println("Account Number should only contains numbers");
            fundTransferScreen1();
        } else if (amountTransfer > 1000) {
            System.out.println("Maximum amount to transfer is $1000");
            fundTransferScreen1();
        } else if (amountTransfer < 1) {
            System.out.println("Minimum amount to transfer is $1");
            fundTransferScreen1();
        } else if (!UtilCls.isNumeric(refNumb)) {
            System.out.println("Invalid Reference Number");
            fundTransferScreen1();
        } else if ((consoleScrSvc.getAuthAccount().getBalance()-amountTransfer)<0) {
            System.out.printf("Insufficient balance $" +amountTransfer);
            fundTransferScreen1();
        } else {
            switch (choice) {
                case 1:
                    consoleScrSvc.getTransactionService().fundTransfer(consoleScrSvc.getTransactionService().getBank().getAccountByAccountNumber(
                            consoleScrSvc.getAuthAccount().getAccountNumber()),
                            consoleScrSvc.getTransactionService().getBank().getAccountByAccountNumber(destAccountNumb), amountTransfer);
                    fundTransferSummaryScreen(destAccountNumb, amountTransfer, refNumb);
                    break;
                case 2:
                    ConsoleMainMenuScrSvc consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
                    consoleMainMenuScrSvc.transactionScreen();
                    break;
            }
        }
    }

    public void fundTransferSummaryScreen(String destAccountNumb, long amountTransfer, String refNumb) {
        System.out.println("Fund Transfer Summary");
        System.out.printf("Destination Account :%s%n", destAccountNumb);
        System.out.printf("Transfer Amount     :$%d%n", amountTransfer);
        System.out.printf("Reference Number    :%s%n", refNumb);
        System.out.printf("Balance             :$%d%n", consoleScrSvc.getAuthAccount().getBalance());
        if (consoleScrSvc.getIsAuto()) {
            ConsoleMainMenuScrSvc consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
            consoleMainMenuScrSvc.chooseTransactionOrWelcomeScreen();
        }
    }

    public IScreenService getConsoleScrSvc() {
        return consoleScrSvc;
    }

    public void setConsoleScrSvc(IScreenService consoleScrSvc) {
        this.consoleScrSvc = consoleScrSvc;
    }
}
