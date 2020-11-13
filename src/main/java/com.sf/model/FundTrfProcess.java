package com.sf.model;

public class FundTrfProcess {
    private String destAccount;
    private Long amount;
    private String refNumber;

    public String getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(String destAccount) {
        this.destAccount = destAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }
}
