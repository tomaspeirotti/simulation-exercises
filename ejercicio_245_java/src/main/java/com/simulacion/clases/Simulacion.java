package com.simulacion.clases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Simulacion {
    private static final Logger LOGGER = LoggerFactory.getLogger(Simulacion.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Script started");
        SimulationBusiness simulationBusiness = new SimulationBusiness();
        simulationBusiness.printCSV();
        LOGGER.info("Script finished");
    }
}
