package com.sf.service.impl.test;

import com.sf.dao.IBank;
import com.sf.dao.impl.MemoryBank;
import com.sf.dao.impl.MemoryMutation;
import com.sf.input.IInput;
import com.sf.service.IScreenService;
import com.sf.service.impl.ConsoleScreenService;
import com.sf.service.impl.MemoryTransactionService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsoleScreenServiceTest {

    IScreenService consoleScreenService;
    IInput consoleInputStub = new ConsoleInputStub();
    IBank bank = new MemoryBank();
    @BeforeEach
    void setUp() {
         consoleScreenService = new ConsoleScreenService(bank,
                new MemoryTransactionService(new MemoryMutation()), consoleInputStub);
        consoleScreenService.setAuto(false);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void login() {
        consoleScreenService.welcomeScreen();
        assertEquals(consoleScreenService.getAuthAccount().getAccountNumber(),consoleInputStub.getAccountNumber());
        assertEquals(consoleScreenService.getAuthAccount().getPin(),consoleInputStub.getPin());
        assertNotNull(consoleScreenService.getAuthAccount());
    }

    @Test
    @Order(2)
    void withdraw() {
        consoleScreenService.welcomeScreen();
        long oldBalance = consoleScreenService.getAuthAccount().getBalance();
        consoleScreenService.withdrawScreen1();
        assertEquals(consoleScreenService.getAuthAccount().getBalance(),
                oldBalance-consoleInputStub.getAmount());
    }

    @Test
    @Order(3)
    void fundTransfer() {
        consoleScreenService.welcomeScreen();
        long oldBalanceOrig = consoleScreenService.getAuthAccount().getBalance();
        long oldBalanceDest = bank.getAccountByAccountNumber(consoleInputStub.getDestAccountNumber()).getBalance();
        consoleScreenService.fundTransferScreen1();
        assertEquals(consoleScreenService.getAuthAccount().getBalance(),
                oldBalanceOrig-consoleInputStub.getAmount());
        assertEquals(bank.getAccountByAccountNumber(consoleInputStub.getDestAccountNumber()).getBalance(),
                oldBalanceDest+consoleInputStub.getAmount());
    }

}
