package com.sf;

import com.sf.dao.impl.Bank;
import com.sf.dao.impl.Mutation;
import com.sf.exception.atmSimulationException;
import com.sf.input.impl.ScannerCls;
import com.sf.service.IScreenService;
import com.sf.service.impl.ScreenService;
import com.sf.service.impl.TransactionService;

//The Factory
public class AtmSimulation {
    public static IScreenService create(String fileCsv) throws atmSimulationException {
        return  new ScreenService(new Bank(fileCsv),
                new TransactionService(new Mutation()),
                new ScannerCls());
    }
}
