package com.sf.service.impl;

import com.sf.input.IInput;
import com.sf.model.Account;
import com.sf.dao.IBank;
import com.sf.service.IScreenService;
import com.sf.service.ITransactionService;
import com.sf.util.UtilCls;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenService implements IScreenService {

    private IBank theBank;
    private IInput inp;
    private Account authAccount = null;
    private ITransactionService transactionService;

    public ScreenService(IBank bank, ITransactionService transactionService, IInput inp) {
        this.setBank(bank);
        this.setTransactionService(transactionService);
        this.inp = inp;
    }

    @Override
    public void run() {
        try {
            this.theBank.AddDefaultAccount();
            this.welcomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void welcomeScreen() {
        String accountNumber;
        String pin;
        this.inp.RefreshDevice();
        this.authAccount = null;
        System.out.println();
        System.out.println(">>>>>>>>>>>>>>>  ATM SIMULATION  <<<<<<<<<<<<<<<<<");
        do {
            System.out.print("Enter Account Number: ");
            accountNumber = inp.getString();
            System.out.print("Enter PIN: ");
            pin = inp.getString();

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

            this.setAuthAccount(this.theBank.userLogin(accountNumber, pin));
            if (this.authAccount == null) {
                System.out.println("Invalid Account Number/PIN");
                continue;
            }
        } while (this.authAccount == null);
        transactionScreen();
    }

    @Override
    public  void transactionScreen(){
        int choice;

        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Print Transaction");
            System.out.println("4. Exit");
            System.out.println("Please choose option[4]:");
            choice = inp.getInt();
        } while(choice <1 || choice >4);

        switch(choice) {
            case 1:
                withdrawScreen1();
                break;
            case 2:
                fundTransferScreen1();
                break;
            case 3:
                printTransactionScreen();
                break;
            case 4:
                welcomeScreen();
                break;

        }
    }

    private void printTransactionScreen() {
        this.getTransactionService().printTransactionScreen(authAccount.getAccountNumber());
        ChooseTransactionOrWelcomeScreen();
    }

    private void ChooseTransactionOrWelcomeScreen() {
        int choice;
        do {
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.println("Choose option[2]:");
            choice = inp.getInt();
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


    private  void withdrawScreen1() {
        int choice;

        do{
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.println("Please choose option[5]:");
            choice = this.inp.getInt();
        } while(choice <1 || choice >5);

        switch(choice){
            case 1:
                this.getTransactionService().withdraw(authAccount, 10);
                withdrawSummaryScreen(10);
                break;
            case 2:
                this.getTransactionService().withdraw(authAccount, 50);
                withdrawSummaryScreen(50);
                break;
            case 3:
                this.getTransactionService().withdraw(authAccount, 100);
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
            amountWithdraw = this.inp.getLong();
            if (amountWithdraw < 0) {
                System.out.println("Invalid amount");
            } else if (!((amountWithdraw % 10)==0)) {
                System.out.println("Invalid amount");
            } else if (!UtilCls.isNumeric(String.valueOf(amountWithdraw))) {
                System.out.println("Invalid amount");
            }  else if ((this.authAccount.getBalance()-amountWithdraw) >= 0) {
                System.out.printf("Insufficient balance : $%d%n", amountWithdraw);
            }
        } while (amountWithdraw < 0 || (amountWithdraw > this.authAccount.getBalance()));
        this.getTransactionService().withdraw(this.authAccount, amountWithdraw);
        withdrawSummaryScreen(amountWithdraw);
    }

    private  void withdrawSummaryScreen(long amountWithdraw) {
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(Calendar.getInstance().getTime());
        System.out.printf("Date     : %s%n", date);
        System.out.printf("Withdraw : $%d%n", amountWithdraw);
        System.out.printf("Balance  : $%d%n", this.authAccount.getBalance());
        ChooseTransactionOrWelcomeScreen();
    }

    private void fundTransferScreen1() {
        String destAccountNumb;
        System.out.println("Please enter destination account and press enter to continue or");
        System.out.println("press enter to go back Transaction: ");
        destAccountNumb = this.inp.getString();
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
            amountTransfer = this.inp.getLong();
        } while (amountTransfer < 0);
        fundTransferScreen3(destAccountNumb, amountTransfer);
    }

    private void fundTransferScreen3(String destAccountNumb, long amountTransfer) {
        String keyIn;
        int refNumb = UtilCls.random6Digits();
        Integer refNumbInt = new Integer(refNumb);
        System.out.printf("Reference Number: %s%n", refNumbInt.toString());
        System.out.println("press enter to continue or press T to go back to Transaction: ");
        keyIn = this.inp.getString();
        if (keyIn.toUpperCase().equals("T")) {
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
        System.out.printf("Reference Number    :%s%n", refNumb);
        System.out.println();
        do {
            System.out.println("1. Confirm Trx");
            System.out.println("2. Cancel Trx");
            System.out.print("Choose option[2]:");
            choice = this.inp.getInt();
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
        } else if ((this.authAccount.getBalance()-amountTransfer)<0) {
            System.out.printf("Insufficient balance $" +amountTransfer);
            fundTransferScreen1();
        } else {
            switch (choice) {
                case 1:
                    this.getTransactionService().fundTransfer(this.theBank.getAccountByAccountNumber(
                            this.authAccount.getAccountNumber()),
                            this.theBank.getAccountByAccountNumber(destAccountNumb), amountTransfer);
                    fundTransferSummaryScreen(destAccountNumb, amountTransfer, refNumb);
                    break;
                case 2:
                    transactionScreen();
                    break;
            }
        }
    }

    private void fundTransferSummaryScreen(String destAccountNumb, long amountTransfer, String refNumb) {
        System.out.println("Fund Transfer Summary");
        System.out.printf("Destination Account :%s%n", destAccountNumb);
        System.out.printf("Transfer Amount     :$%d%n", amountTransfer);
        System.out.printf("Reference Number    :%s%n", refNumb);
        System.out.printf("Balance             :$%d%n", this.authAccount.getBalance());
        ChooseTransactionOrWelcomeScreen();
    }

    @Override
    public IBank getBank() {
        return theBank;
    }

    public void setBank(IBank bank) {
        this.theBank = bank;
    }

    @Override
    public Account getAuthAccount() {
        return authAccount;
    }

    @Override
    public void setAuthAccount(Account authAccount) {
        this.authAccount = authAccount;
    }

    @Override
    public ITransactionService getTransactionService() {
        return transactionService;
    }

    @Override
    public void setTransactionService(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

}
