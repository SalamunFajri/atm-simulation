package com.sf.model;

public class Transaction {

    public enum TransactionType {
        WITHDRAW, FUND_TRANSFER_IN, FUND_TRANSFER_OUT
    }

    private long id;
    private String accountNumber;
    private String timestamp;
    private String transactionType;
    private long amount;

    public Transaction(long id, String accountNumber, String timestamp, String transactionType, long amount) {
        this.setId(id);
        this.setAccountNumber(accountNumber);
        this.setTimestamp(timestamp);
        this.setTransactionType(transactionType);
        this.setAmount(amount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
