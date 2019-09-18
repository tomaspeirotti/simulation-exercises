package com.simulation.rest.controllers;

import com.simulation.model.clases.Iteracion;
import com.simulation.model.clases.IterationsResponseDTO;
import com.simulation.model.dto.ParametrosDTO;
import com.simulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class SimulationController {

  private SimulationService simulationService;

  @Autowired
  public SimulationController(SimulationService simulationService) {
    this.simulationService = simulationService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "simulate")
  public ResponseEntity start(@RequestBody ParametrosDTO params) {
    this.simulationService.startSimulation(params);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(method = RequestMethod.GET, value = "iterations")
  public IterationsResponseDTO getPage(int page, int size) {
    return this.simulationService.getIterations(page, size);
  }
}
