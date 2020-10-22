package com.sf.service;

import com.sf.model.Account;
import com.sf.model.Mutation;
import com.sf.model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionService {

    private Mutation mutation;

    public TransactionService(Mutation mutation) {
        this.setMutation(mutation);
    }

    public  void withdraw(Account account, long amount) {
        account.setBalance(account.getBalance()-amount);

        this.mutation.Add(new Transaction(this.mutation.getNextId(),
                account.getAccountNumber(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.WITHDRAW.toString(), amount));
    }

    public  void fundTransfer(Account origAccount, Account destAccount, long amount) {
        origAccount.setBalance(origAccount.getBalance()-amount);
        this.mutation.Add(new Transaction(this.mutation.getNextId(),
                origAccount.getAccountNumber(),
                LocalDateTime.now().minusSeconds(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_OUT.toString(),
                amount));
        destAccount.setBalance(destAccount.getBalance()+amount);
        this.mutation.Add(new Transaction(this.mutation.getNextId(),
                destAccount.getAccountNumber(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_IN.toString(),
                amount));

    }

    public void printTransactionScreen(String accountNumber) {
        List<Transaction> list = this.mutation.getLastNTransaction(accountNumber, 10);
        list.stream()
                .forEach(t->System.out.println(t.getAccountNumber() + ","
                        +t.getTimestamp()+","
                        +t.getTransactionType()+","
                        +t.getAmount()));
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }
}
