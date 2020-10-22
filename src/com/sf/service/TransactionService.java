package com.sf.service;

import com.sf.model.Account;
import com.sf.model.Mutation;
import com.sf.model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransactionService {

    private Mutation mutation;

    public TransactionService(Mutation mutation) {
        this.setMutation(mutation);
    }

    public  void withdraw(Account account, long amount) {
        account.setBalance(account.getBalance()-amount);

        this.getMutation().Add(new Transaction(getMutation().getNextId(),
                account.getAccountNumber(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.WITHDRAW.toString(), amount));
    }

    public  void fundTransfer(Account origAccount, Account destAccount, long amount) {
        origAccount.setBalance(origAccount.getBalance()-amount);
        this.getMutation().Add(new Transaction(getMutation().getNextId(),
                origAccount.getAccountNumber(),
                LocalDateTime.now().minusSeconds(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_OUT.toString(),
                amount));
        destAccount.setBalance(destAccount.getBalance()+amount);
        this.getMutation().Add(new Transaction(getMutation().getNextId(),
                destAccount.getAccountNumber(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_IN.toString(),
                amount));

    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }
}
