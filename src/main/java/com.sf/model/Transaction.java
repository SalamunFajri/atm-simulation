package com.sf.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transaction")
public class Transaction {

    public enum TransactionType {
        WITHDRAW, FUND_TRANSFER_IN, FUND_TRANSFER_OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "transactionType")
    private String transactionType;
    @Column(name = "amount")
    private long amount;

    public Transaction(long id, String accountNumber, Date timestamp, String transactionType, long amount) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
