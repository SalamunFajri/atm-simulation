package com.sf;

import com.sf.dao.impl.Bank;
import com.sf.dao.impl.Mutation;
import com.sf.exception.atmSimulationException;
import com.sf.service.impl.ScreenService;
import com.sf.service.impl.TransactionService;

public class Main {
    public static void main(String[] args) throws atmSimulationException {
        AtmSimFactory.create(new ScreenService(new Bank(args[0]), new TransactionService(new Mutation())));
    }
}
