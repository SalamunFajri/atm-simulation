package com.sf.service;

import com.sf.dao.impl.MemoryBank;
import com.sf.dao.impl.MemoryMutation;
import com.sf.service.impl.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsoleScrSvcTest {

    IScreenService consoleScrSvc;
    ConsoleMainMenuScrSvc consoleMainMenuScrSvc;
    @BeforeEach
    void setUp() {
        consoleScrSvc = new ConsoleScrSvc(
                new MemoryTransactionService(new MemoryBank(), new MemoryMutation()),
                new ConsoleInputStub());

        consoleMainMenuScrSvc = new ConsoleMainMenuScrSvc(consoleScrSvc);
        consoleScrSvc.setIsAuto(false);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void login() {
        consoleMainMenuScrSvc.welcomeScreen();
        assertEquals(consoleScrSvc.getAuthAccount().getAccountNumber(),consoleScrSvc.getInp().getAccountNumber());
        assertEquals(consoleScrSvc.getAuthAccount().getPin(),consoleScrSvc.getInp().getPin());
        assertNotNull(consoleScrSvc.getAuthAccount());
    }

    @Test
    @Order(2)
    void withdraw() {
        consoleMainMenuScrSvc.welcomeScreen();
        long oldBalance = consoleScrSvc.getAuthAccount().getBalance();
        ConsoleWidrawScrSvc consoleWidrawScrSvc = new ConsoleWidrawScrSvc(consoleScrSvc);
        consoleWidrawScrSvc.withdrawScreen1();
        assertEquals(consoleScrSvc.getAuthAccount().getBalance(),
                oldBalance-consoleScrSvc.getInp().getAmount());
    }

    @Test
    @Order(3)
    void fundTransfer() {
        consoleMainMenuScrSvc.welcomeScreen();
        long oldBalanceOrig = consoleScrSvc.getAuthAccount().getBalance();
        long oldBalanceDest = consoleScrSvc.getTransactionService().getBank().getAccountByAccountNumber(consoleScrSvc.getInp().getDestAccountNumber()).getBalance();
        ConsoleFundTrfScrSvc consoleFundTrfScrSvc = new ConsoleFundTrfScrSvc(consoleScrSvc);
        consoleFundTrfScrSvc.fundTransferScreen1();
        assertEquals(consoleScrSvc.getAuthAccount().getBalance(),
                oldBalanceOrig-consoleScrSvc.getInp().getAmount());
        assertEquals(consoleScrSvc.getTransactionService().getBank().getAccountByAccountNumber(consoleScrSvc.getInp().getDestAccountNumber()).getBalance(),
                oldBalanceDest+consoleScrSvc.getInp().getAmount());
    }

}
