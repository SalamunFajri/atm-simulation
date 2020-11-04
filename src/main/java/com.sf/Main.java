package com.sf;

import com.sf.exception.atmSimulationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner{

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        AtmSimulation
                .createConsole(args.length>=1?args[0]:null)
                .run();
    }
}

/*
public class Main {
    public static void main(String[] args) throws atmSimulationException {
        AtmSimulation
                .createConsole(args.length>=1?args[0]:null)
                .run();
    }
}
 */
