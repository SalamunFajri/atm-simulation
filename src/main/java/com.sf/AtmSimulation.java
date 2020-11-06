package com.sf;

import com.sf.dao.impl.MemoryBank;
import com.sf.dao.impl.MemoryMutation;
import com.sf.input.impl.ConsoleInput;
import com.sf.service.impl.ConsoleScrSvc;
import com.sf.service.impl.MemoryTransactionService;
import com.sf.service.impl.ConsoleMainMenuScrSvc;

//The Factory
public class AtmSimulation {
    public static ConsoleMainMenuScrSvc createConsole(String fileCsv)  {
        return  new ConsoleMainMenuScrSvc(
                new ConsoleScrSvc(
                new MemoryTransactionService(new MemoryBank(fileCsv), new MemoryMutation()),
                new ConsoleInput()));
    }

    /*
    public static ConsoleMainMenuScrSvc createWeb(String fileCsv) throws atmSimulationException {
        return  new WebMainMenuScrSvc(
                new WebScrSvc(new MemoryBank(fileCsv),
                new MemoryTransactionService(new MemoryMutation()),
                new WebInput()));
    }
    */
}
