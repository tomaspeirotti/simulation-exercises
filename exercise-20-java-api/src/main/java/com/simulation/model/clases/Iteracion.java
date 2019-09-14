package com.simulation.model.clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iteracion {

  private Long dia;
  private Demanda demanda;
  private Pedido pedido;
  private Ks ks;
  private double km;
  private double ko;


}
