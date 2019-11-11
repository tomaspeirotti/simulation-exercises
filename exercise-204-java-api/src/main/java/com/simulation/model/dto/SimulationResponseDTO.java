package com.simulation.model.dto;

import com.simulation.model.clases.Politica;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationResponseDTO {
  double balancePoliticaA;
  double balancePoliticaB;
  double balancePoliticaC;
  Politica politicaGanadora;
}
