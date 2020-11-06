package com.sf.service.impl;

import com.sf.dao.IBank;
import com.sf.model.Account;
import com.sf.dao.IMutation;
import com.sf.model.Transaction;
import com.sf.service.ITransactionService;
import com.sf.util.UtilDate;

import java.time.LocalDateTime;

public class MemoryTransactionService implements ITransactionService {

    private IMutation mutation;
    private IBank bank;

    public MemoryTransactionService(IBank bank, IMutation mutation) {
        this.setMutation(mutation);
        this.setBank(bank);
    }

    @Override
    public  void withdrawFrom(Account account, long amount) {
        this.bank.updateBalance(account, account.getBalance()-amount);
        this.mutation.add(new Transaction(this.mutation.getNextId(),
                account.getAccountNumber(), UtilDate.asDate(LocalDateTime.now()),
                Transaction.TransactionType.WITHDRAW.toString(), amount));
    }

    @Override
    public  void fundTransfer(Account origAccount, Account destAccount, long amount) {
        this.bank.updateBalance(origAccount,origAccount.getBalance()-amount);
        this.mutation.add(new Transaction(this.mutation.getNextId(),
                origAccount.getAccountNumber(),
                UtilDate.asDate(LocalDateTime.now().minusSeconds(1)),
                Transaction.TransactionType.FUND_TRANSFER_OUT.toString(),
                amount));
        this.bank.updateBalance(destAccount,destAccount.getBalance()+amount);
        this.mutation.add(new Transaction(this.mutation.getNextId(),
                destAccount.getAccountNumber(),
                UtilDate.asDate(LocalDateTime.now()),
                Transaction.TransactionType.FUND_TRANSFER_IN.toString(),
                amount));
    }

    @Override
    public IMutation getMutation() {
        return mutation;
    }

    @Override
    public void setMutation(IMutation mutation) {
        this.mutation = mutation;
    }

    @Override
    public IBank getBank() {
        return bank;
    }

    @Override
    public void setBank(IBank bank) {
        this.bank = bank;
    }
}
