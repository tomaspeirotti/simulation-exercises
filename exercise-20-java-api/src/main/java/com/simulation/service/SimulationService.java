package com.simulation.service;

import com.simulation.model.clases.IterationsResponseDTO;
import com.simulation.model.dto.ParametrosDTO;
import org.springframework.stereotype.Service;

@Service
public interface SimulationService {
    void startSimulation(ParametrosDTO parametrosDTO);
    IterationsResponseDTO getIterations(int page, int size);
}
