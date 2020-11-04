package com.sf.service.impl;

import com.sf.model.Account;
import com.sf.dao.IMutation;
import com.sf.model.Transaction;
import com.sf.service.ITransactionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MemoryTransactionService implements ITransactionService {

    private IMutation mutation;

    public MemoryTransactionService(IMutation mutation) {
        this.setMutation(mutation);
    }

    @Override
    public  void withdrawFrom(Account account, long amount) {
        account.setBalance(account.getBalance()-amount);

        this.mutation.add(new Transaction(this.mutation.getNextId(),
                account.getAccountNumber(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.WITHDRAW.toString(), amount));
    }

    @Override
    public  void fundTransfer(Account origAccount, Account destAccount, long amount) {
        origAccount.setBalance(origAccount.getBalance()-amount);
        this.mutation.add(new Transaction(this.mutation.getNextId(),
                origAccount.getAccountNumber(),
                LocalDateTime.now().minusSeconds(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_OUT.toString(),
                amount));
        destAccount.setBalance(destAccount.getBalance()+amount);
        this.mutation.add(new Transaction(this.mutation.getNextId(),
                destAccount.getAccountNumber(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Transaction.TransactionType.FUND_TRANSFER_IN.toString(),
                amount));

    }

    @Override
    public void printTransactionScreen(String accountNumber) {
        List<Transaction> list = this.mutation.getLastNTransaction(accountNumber, 10);
        list.stream()
                .forEach(t->System.out.println(t.getAccountNumber() + ","
                        +t.getTimestamp()+","
                        +t.getTransactionType()+","
                        +t.getAmount()));
    }

    @Override
    public IMutation getMutation() {
        return mutation;
    }

    @Override
    public void setMutation(IMutation mutation) {
        this.mutation = mutation;
    }
}
