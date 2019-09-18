package com.simulation.model.clases;

import lombok.Data;

@Data
public class Pedido {
  private double rnd;
  private int plazoDeReposicion;
  private int ultimaReposicion;
  private int diaDeResposicion;
}
