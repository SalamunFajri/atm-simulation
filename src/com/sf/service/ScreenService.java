package com.sf.service;

import com.sf.model.Account;
import com.sf.model.Bank;
import com.sf.util.UtilCls;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class ScreenService {

    private Bank theBank;
    private Scanner sc;
    private Account authAccount = null;

    public ScreenService(Bank bank) {
        this.setBank(bank);
        this.setSc(new Scanner(System.in));
    }

    public void Run() {
        this.welcomeScreen();
    }

    public void welcomeScreen() {
        String accountNumber;
        String pin;
        this.setSc(new Scanner(System.in));
        authAccount = null;
        do {
            System.out.print("Enter Account Number: ");
            accountNumber = sc.nextLine();
            System.out.print("Enter PIN: ");
            pin = sc.nextLine();

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

            this.setAuthAccount(theBank.userLogin(accountNumber, pin));
            if (authAccount == null) {
                System.out.println("Invalid Account Number/PIN");
                continue;
            }
        } while (authAccount == null);
        transactionScreen();
    }

    public  void transactionScreen(){
        int choice;

        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.println("Please choose option[3]:");
            choice = sc.nextInt();
        } while(choice <1 || choice >3);

        switch(choice){
            case 1:
                withdrawScreen1();
                break;
            case 2:
                fundTransferScreen1();
                break;
            case 3:
                welcomeScreen();
                break;
        }
    }


    private  void withdrawScreen1() {
        int choice;

        do{
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.println("Please choose option[5]:");
            choice = sc.nextInt();
        } while(choice <1 || choice >5);

        switch(choice){
            case 1:
                BankService.withdraw(authAccount, 10);
                withdrawSummaryScreen(10);
                break;
            case 2:
                BankService.withdraw(authAccount, 50);
                withdrawSummaryScreen(50);
                break;
            case 3:
                BankService.withdraw(authAccount, 100);
                withdrawSummaryScreen(100);
                break;
            case 4:
                withdrawScreen2();
                break;
            case 5:
                transactionScreen();
                break;
        }
    }


    private  void withdrawScreen2() {
        long amountWithdraw;

        System.out.println("Other Withdraw");
        do {
            System.out.println("Enter amount to withdraw:");
            amountWithdraw = sc.nextLong();
            if (amountWithdraw < 0) {
                System.out.println("Invalid amount");
                continue;
            } else if (!((amountWithdraw % 10)==0)) {
                System.out.println("Invalid amount");
                continue;
            } else if (!UtilCls.isNumeric(String.valueOf(amountWithdraw))) {
                System.out.println("Invalid amount");
                continue;
            }  else if ((authAccount.getBalance()-amountWithdraw) >= 0) {
                System.out.printf("Insufficient balance :", amountWithdraw);
                continue;
            }
        } while (amountWithdraw < 0 || (amountWithdraw > authAccount.getBalance()));
        BankService.withdraw(authAccount, amountWithdraw);
        withdrawSummaryScreen(amountWithdraw);
    }

    private  void withdrawSummaryScreen(long amountWithdraw) {
        int choice;
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(Calendar.getInstance().getTime());
        System.out.printf("Date     : %s%n", date);
        System.out.printf("Withdraw : $%d%n", amountWithdraw);
        System.out.printf("Balance  : $%d%n", authAccount.getBalance());
        System.out.println("");
        do {
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.println("Choose option[2]:");
            choice = sc.nextInt();
        }while(choice <1 || choice >2);

        switch(choice){
            case 1:
                transactionScreen();
                break;
            case 2:
                welcomeScreen();
                break;
        }
    }

    private void fundTransferScreen1() {
        sc.nextLine();
        String destAccountNumb;
        System.out.println("Please enter destination account and");
        System.out.println("press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction: ");
        destAccountNumb = sc.nextLine();
        if (destAccountNumb.length()>0) {
            fundTransferScreen2(destAccountNumb);
        } else {
            transactionScreen();
        }
    }

    private void fundTransferScreen2(String destAccountNumb) {
        long amountTransfer;
        do {
            System.out.println("Please enter transfer amount and press enter to continue or");
            System.out.println("press enter to go back to Transaction:");
            amountTransfer = sc.nextLong();
        } while (amountTransfer < 0);
        fundTransferScreen3(destAccountNumb, amountTransfer);
    }

    private void fundTransferScreen3(String destAccountNumb, long amountTransfer) {
        sc.nextLine();
        String keyIn = null;
        int refNumb = UtilCls.random6Digits();
        Integer refNumbInt = new Integer(refNumb);
        System.out.printf("Reference Number: %s%n", refNumbInt.toString());
        System.out.println("press enter to continue or press T to go back to Transaction: ");
        keyIn = sc.nextLine();
        if (keyIn.toUpperCase() == "T") {
            transactionScreen();
        } else {
            fundTransferScreen4(destAccountNumb, amountTransfer, refNumbInt.toString());
        }
    }

    private void fundTransferScreen4(String destAccountNumb, long amountTransfer, String refNumb) {
        int choice;
        System.out.println("Transfer Confirmation");
        System.out.printf("Destination Account :%s%n", destAccountNumb);
        System.out.printf("Transfer Amount     :$%d%n", amountTransfer);
        System.out.printf("Reference Number    :$%s%n", refNumb);
        System.out.println("");
        do {
            System.out.println("1. Confirm Trx");
            System.out.println("2. Cancel Trx");
            System.out.printf("Choose option[2]:");
            choice = sc.nextInt();
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
        } else if ((authAccount.getBalance()-amountTransfer)<0) {
            System.out.printf("Insufficient balance $" +amountTransfer);
            fundTransferScreen1();
        } else {
            switch (choice) {
                case 1:
                    BankService.fundTransfer(theBank.getAccount(authAccount.getAccountNumber()),
                            theBank.getAccount(destAccountNumb), amountTransfer);
                    fundTransferSummaryScreen(destAccountNumb, amountTransfer, refNumb.toString());
                    break;
                case 2:
                    transactionScreen();
                    break;
            }
        }
    }

    private void fundTransferSummaryScreen(String destAccountNumb, long amountTransfer, String refNumb) {
        int choice;
        System.out.println("Fund Transfer Summary");
        System.out.printf("Destination Account :$%s%n", destAccountNumb);
        System.out.printf("Transfer Amount     :$%d%n", amountTransfer);
        System.out.printf("Reference Number    :$%s%n", refNumb);
        System.out.printf("Balance             :$%d%n", authAccount.getBalance());
        System.out.println("");
        do {
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.printf("Choose option[2]:");
            choice = sc.nextInt();
        } while(choice <1 || choice >2);

        switch (choice) {
            case 1:
                transactionScreen();
                break;
            case 2:
                welcomeScreen();
                break;
        }

    }

    public Bank getBank() {
        return theBank;
    }

    public void setBank(Bank bank) {
        this.theBank = bank;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public Account getAuthAccount() {
        return authAccount;
    }

    public void setAuthAccount(Account authAccount) {
        this.authAccount = authAccount;
    }
}
