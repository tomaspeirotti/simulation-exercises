package com.simulacion.clases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Simulacion {
    private static final Logger LOGGER = LoggerFactory.getLogger(Simulacion.class);

    public static void main(String[] args) throws Exception {
        SimulationBusiness simulationBusiness = new SimulationBusiness();
        CSVBusiness csvBusiness = new CSVBusiness();
        LOGGER.info("Simulation started");
        List<Iteracion> iteraciones = simulationBusiness.startSimulation();
        LOGGER.info("Simulation finished, creating CSV...");
        csvBusiness.printCSV(iteraciones, simulationBusiness.getColumnasPredeterminadas());
        LOGGER.info("CSV Created");
    }
}
