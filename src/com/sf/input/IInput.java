package com.sf.input;

public interface IInput {
    int getInt();

    long getLong();

    String getString();

    void RefreshDevice();

    String getAccountNumber();

    String getPin();

    int getTransactionChoice();

    int getWithDrawChoice();

    long getAmount();

    String getDestAccountNumber();

    int getConfirmChoice();
}
