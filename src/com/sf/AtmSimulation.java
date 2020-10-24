package com.sf;

import com.sf.dao.impl.MemoryBank;
import com.sf.dao.impl.MemoryMutation;
import com.sf.dao.impl.SqlDbBank;
import com.sf.dao.impl.SqlDbMutation;
import com.sf.exception.atmSimulationException;
import com.sf.input.impl.ConsoleInput;
import com.sf.input.impl.WebInput;
import com.sf.service.IScreenService;
import com.sf.service.impl.ConsoleScreenService;
import com.sf.service.impl.MemoryTransactionService;
import com.sf.service.impl.SqlDbTransactionService;

//The Factory
public class AtmSimulation {
    public static IScreenService createConsole(String fileCsv) throws atmSimulationException {
        return  new ConsoleScreenService(new MemoryBank(fileCsv),
                new MemoryTransactionService(new MemoryMutation()),
                new ConsoleInput());
    }

    public static IScreenService createWeb(String fileCsv) throws atmSimulationException {
        return  new ConsoleScreenService(new SqlDbBank(fileCsv),
                new SqlDbTransactionService(new SqlDbMutation()),
                new WebInput());
    }
}
