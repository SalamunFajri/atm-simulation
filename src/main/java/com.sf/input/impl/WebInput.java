package com.sf.input.impl;

import com.sf.input.IInput;

public class WebInput implements IInput {
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
        return null;
    }

    @Override
    public void RefreshDevice() {

    }

    @Override
    public String getAccountNumber() {
        return null;
    }

    @Override
    public String getPin() {
        return null;
    }

    @Override
    public int getTransactionChoice() {
        return 0;
    }

    @Override
    public int getWithDrawChoice() {
        return 0;
    }

    @Override
    public long getAmount() {
        return 0;
    }

    @Override
    public String getDestAccountNumber() {
        return null;
    }

    @Override
    public int getConfirmChoice() {
        return 0;
    }
}
