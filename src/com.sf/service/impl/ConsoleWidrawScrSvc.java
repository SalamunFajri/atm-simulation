package com.sf.service.impl;

import com.sf.service.IScreenService;
import com.sf.util.UtilCls;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsoleWidrawScrSvc {

    private IScreenService consoleScrSvc;

    public ConsoleWidrawScrSvc(IScreenService consoleScrSvc)  {
        this.setConsoleScrSvc(consoleScrSvc);
    }

    public  void withdrawScreen1() {
        int choice;

        do{
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.println("Please choose option[5]:");
            choice = consoleScrSvc.getInp().getWithDrawChoice();
        } while(choice <1 || choice >5);

        switch(choice){
            case 1:
                consoleScrSvc.getTransactionService().withdrawFrom(
                        consoleScrSvc.getAuthAccount(), 10);
                withdrawSummaryScreen(10);
                break;
            case 2:
                consoleScrSvc.getTransactionService().withdrawFrom(
                        consoleScrSvc.getAuthAccount(), 50);
                withdrawSummaryScreen(50);
                break;
            case 3:
                consoleScrSvc.getTransactionService().withdrawFrom(
                        consoleScrSvc.getAuthAccount(), 100);
                withdrawSummaryScreen(100);
                break;
            case 4:
                withdrawScreen2();
                break;
            case 5:
                ConsoleMainMenuScrSvc consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
                consoleMainMenuScrSvc.transactionScreen();
                break;
        }
    }

    public  void withdrawScreen2() {
        long amountWithdraw;

        System.out.println("Other Withdraw");
        do {
            System.out.println("Enter amount to withdraw:");
            amountWithdraw = consoleScrSvc.getInp().getAmount();
            if (amountWithdraw < 0) {
                System.out.println("Invalid amount");
            } else if (!((amountWithdraw % 10)==0)) {
                System.out.println("Invalid amount");
            } else if (!UtilCls.isNumeric(String.valueOf(amountWithdraw))) {
                System.out.println("Invalid amount");
            }  else if ((consoleScrSvc.getAuthAccount().getBalance()-amountWithdraw) < 0) {
                System.out.printf("Insufficient balance : $%d%n", amountWithdraw);
            }
        } while (amountWithdraw < 0 || (amountWithdraw > consoleScrSvc.getAuthAccount().getBalance()));
        consoleScrSvc.getTransactionService().withdrawFrom(
                consoleScrSvc.getAuthAccount(), amountWithdraw);
        withdrawSummaryScreen(amountWithdraw);
    }

    public  void withdrawSummaryScreen(long amountWithdraw) {
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(Calendar.getInstance().getTime());
        System.out.printf("Date     : %s%n", date);
        System.out.printf("Withdraw : $%d%n", amountWithdraw);
        System.out.printf("Balance  : $%d%n", consoleScrSvc.getAuthAccount().getBalance());
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
