package com.simulacion.clases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Simulacion {
    private static final Logger LOGGER = LoggerFactory.getLogger(Simulacion.class);

    public static void main(String[] args) throws Exception {
        SimulationBusiness simulationBusiness = new SimulationBusiness();
        LOGGER.info("Simulation started");
        simulationBusiness.startSimulation();
        LOGGER.info("Simulation finished, creating CSV...");
        simulationBusiness.printCSV();
        LOGGER.info("CSV Created");
    }
}
