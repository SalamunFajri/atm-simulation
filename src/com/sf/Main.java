package com.sf;

import com.sf.exception.atmSimulationException;
import com.sf.model.Bank;
import com.sf.model.Mutation;
import com.sf.service.ScreenService;
import com.sf.service.TransactionService;

public class Main {

    public static void main(String[] args) {
        Mutation mutation = new Mutation();
        ScreenService screenService = null;
        try {
            screenService = new ScreenService(new Bank(args[0]),new TransactionService(mutation), mutation);
        } catch (atmSimulationException e) {
            System.out.println(e.getMessage());
        } finally {
            if (screenService == null) {
                screenService = new ScreenService(new Bank(),new TransactionService(mutation), mutation);
            }
            screenService.Run();
        }
    }

}
