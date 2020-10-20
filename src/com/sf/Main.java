package com.sf;

import com.sf.model.Bank;
import com.sf.model.Mutation;
import com.sf.model.Transaction;
import com.sf.service.ScreenService;
import com.sf.service.TransactionService;
import com.sf.util.UtilCsv;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ReadCsvFile(args[0]);
        ScreenService screenService = new ScreenService(new Bank(),new TransactionService(), new Mutation());
        screenService.Run();
    }

    private static void ReadCsvFile(String arg) {
        UtilCsv utilCsv = new UtilCsv();
        try {
            utilCsv.readAccountFromCsv(arg);
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: File CSV Not Found");
        }
    }

}
