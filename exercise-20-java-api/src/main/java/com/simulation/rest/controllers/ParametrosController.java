package com.simulation.rest.controllers;

import com.simulation.model.dto.ParametrosDTO;
import com.simulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/simulation")
public class ParametrosController {

    private SimulationService simulationService;

    @Autowired
    public ParametrosController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @RequestMapping(method= RequestMethod.POST, value = "/parametros")
    public void start(@RequestBody ParametrosDTO params) {
        simulationService.startSimulation(params);
    }

}
