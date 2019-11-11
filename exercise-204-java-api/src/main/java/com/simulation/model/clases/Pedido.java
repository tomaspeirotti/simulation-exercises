package com.simulation.model.clases;

import lombok.Data;

@Data
public class Pedido {
  private String rnd;
  private String plazoDeReposicion;
  private String ultimaReposicion;
  private String diaDeResposicion;
  private boolean enCurso;
}
