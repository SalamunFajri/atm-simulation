package com.sf.input.impl;

import com.sf.input.IInput;

import java.util.Scanner;

public class ConsoleInput implements IInput {

    private Scanner sc;
    public ConsoleInput(){
        this.sc = new Scanner(System.in);
    }

    @Override
    public int getInt() {
        int result;
        try {
            result = Integer.parseInt(this.sc.nextLine());
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    @Override
    public long getLong() {
        long result;
        try {
            result = Long.parseLong(this.sc.nextLine());
        } catch (Exception e) {
            result = 0L;
        }
        return result;
    }

    @Override
    public String getString() {
        String result;
        try {
            result = this.sc.nextLine();
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    @Override
    public void RefreshDevice() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String getAccountNumber() {
        return getString();
    }

    @Override
    public String getPin() {
        return getString();
    }

    @Override
    public int getTransactionChoice() {
        return getInt();
    }

    @Override
    public int getWithDrawChoice() {
        return getInt();
    }

    @Override
    public long getAmount() {
        return getLong();
    }

    @Override
    public String getDestAccountNumber() {
        return getString();
    }

    @Override
    public int getConfirmChoice() {
        return getInt();
    }

}
