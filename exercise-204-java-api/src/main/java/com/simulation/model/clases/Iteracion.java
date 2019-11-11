package com.simulation.model.clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iteracion {

  private int dia;
  private Demanda demanda;
  private Pedido pedido;
  private double km;
  private double ko;
  private Ks ks;
  private double costoTotal;
  private double costoAcumulado;

}
