package com.simulation.model.dto;

import lombok.Data;

@Data
public class ParametrosDTO {

    private int diasDeOperacion;
    private int cantReposicion;
    private int nivelReposicion;
    private int diasParaReponer;
    private int inventarioInicial;
    private int costoMantenimiento;
    private int costoOrdenamiento;
    private int costoStockout;

}
