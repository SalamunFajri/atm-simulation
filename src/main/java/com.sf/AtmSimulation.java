package com.sf;

import com.sf.dao.impl.MemoryBank;
import com.sf.dao.impl.MemoryMutation;
import com.sf.input.impl.ConsoleInput;
import com.sf.service.impl.ConsoleScrSvc;
import com.sf.service.impl.TransactionService;
import com.sf.service.impl.ConsoleMainMenuScrSvc;

//The Factory
public class AtmSimulation {
    public static ConsoleMainMenuScrSvc createConsole(String fileCsv)  {
        return  new ConsoleMainMenuScrSvc(
                new ConsoleScrSvc(
                new TransactionService(new MemoryBank(fileCsv), new MemoryMutation()),
                new ConsoleInput()));
    }

    /*
    public static ConsoleMainMenuScrSvc createWeb(String fileCsv) throws atmSimulationException {
        return  new WebMainMenuScrSvc(
                new WebScrSvc(new MemoryBank(fileCsv),
                new TransactionService(new MemoryMutation()),
                new WebInput()));
    }
    */
}
