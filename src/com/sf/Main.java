package com.sf;

import java.util.Scanner;

public class Main {

    public static void main(String[] srgs){
        Scanner sc;
        Bank bank = new Bank();
        Account currAccount;
        while(true){
            sc = new Scanner(System.in);
            currAccount = Main.welcome(bank,sc);
            Main.transactionMenu(currAccount,sc);
        }
    }

    public static Account welcome(Bank theBank, Scanner sc){
        String accountNumber;
        String pin;
        Account authAccount = null;

        do{
            System.out.print("Enter Account Number: " );
            accountNumber = sc.nextLine();
            System.out.print("Enter PIN: ");
            pin = sc.nextLine();

            if (accountNumber.length()!=6){
                System.out.println("Account Number should have 6 digits");
                continue;
            }
            if (!isNumeric(accountNumber)){
                System.out.println("Account Number should only contains numbers");
                continue;
            }
            if (pin.length()!=6){
                System.out.println("PIN should have 6 digits");
                continue;
            }
            if (!isNumeric(pin)){
                System.out.println("PIN should only contains numbers");
                continue;
            }

            authAccount = theBank.userLogin(accountNumber, pin);
            if(authAccount == null){
                System.out.println("Invalid Account Number/PIN");
                continue;
            }
        } while(authAccount == null);

        return authAccount;
    }

    public static void transactionMenu(Account theAccount, Scanner sc){
        int choice;

        do{
            System.out.println(" 1) Withdraw");
            System.out.println(" 2) Fund Transfer");
            System.out.println(" 3) Exit");
            System.out.println("Please choose option[3]:");
            choice = sc.nextInt();
        } while(choice <1 || choice >3);

        switch(choice){
            case 1:
                withdraw(theAccount, sc);
                break;
            case 2:
                fundTransfer(theAccount, sc);
                break;
        }
    }

    private static void fundTransfer(Account theAccount, Scanner sc) {

    }

    private static void withdraw(Account theAccount, Scanner sc) {

    }

    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }


}
