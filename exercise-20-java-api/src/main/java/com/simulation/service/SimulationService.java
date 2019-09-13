package com.simulation.service;

import com.simulation.model.clases.Iteracion;
import com.simulation.model.dto.ParametrosDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimulationService {
    List<Iteracion> startSimulation(ParametrosDTO parametrosDTO);
}
