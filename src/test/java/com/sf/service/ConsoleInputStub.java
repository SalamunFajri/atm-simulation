package com.sf.service;

import com.sf.input.IInput;

public class ConsoleInputStub implements IInput {

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public String getString() {
        return "";
    }

    @Override
    public void RefreshDevice() {

    }

    @Override
    public String getAccountNumber() {
        return "112233";
    }

    @Override
    public String getPin() {
        return "012108";
    }

    @Override
    public int getTransactionChoice() {
        return 1;
    }

    @Override
    public int getWithDrawChoice() {
        return 4;
    }

    @Override
    public long getAmount() {
        return 10;
    }

    @Override
    public String getDestAccountNumber() {
        return "112244";
    }

    @Override
    public int getConfirmChoice() {
        return 1;
    }
}
