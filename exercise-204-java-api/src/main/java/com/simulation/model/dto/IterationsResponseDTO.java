package com.simulation.model.dto;

import com.simulation.model.clases.Iteracion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IterationsResponseDTO {
  List<Iteracion> iteraciones;
  int totalRows;
}
