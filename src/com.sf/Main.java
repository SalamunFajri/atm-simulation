package com.sf;

import com.sf.exception.atmSimulationException;

public class Main {
    public static void main(String[] args) throws atmSimulationException {
        AtmSimulation
                .createConsole(args[0])
                .run();
    }
}
