package com.sf;

import com.sf.dao.impl.AccountRepository;
import com.sf.dao.impl.SqlDbBank;
import com.sf.exception.atmSimulationException;

import com.sf.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main implements CommandLineRunner{


    @Autowired
    private SqlDbBank sqlDbBank;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        AtmSimulation
                .createConsole(args.length>=1?args[0]:null)
                .run();
    }
    */

    @Override
    public void run(String... args) throws atmSimulationException {

        sqlDbBank.addDefaultAccount();

        System.out.println("\nfindAll()");
        sqlDbBank.getAll().forEach(x -> System.out.println(x.getAccountNumber()+";"+x.getPin()));

    }

}


