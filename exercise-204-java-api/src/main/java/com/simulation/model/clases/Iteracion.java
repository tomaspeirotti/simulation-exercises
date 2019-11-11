package com.simulation.model.clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iteracion {

  private Politica politica;
  private int semana;
  private double rnd;
  private Estado estado;
  private int ingresos;
  private int reparacion;
  private int semanasSinReparacion;
  private int cantidadReparaciones;
  private int balance;
}
