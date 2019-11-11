package com.simulation.model.clases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EstadoBuilder {
    private Estado actual;
    private DoubleTuple limites;
    private Estado posibleProximoEstado;
}
