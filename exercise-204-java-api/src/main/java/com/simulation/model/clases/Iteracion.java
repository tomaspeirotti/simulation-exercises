package com.simulation.model.clases;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Iteracion {

  private Politica politica;
  private int semana;
  private double rnd;
  private Estado estado;
  private Estado estadoPrevioReparacion;
  private int ingresos;
  private int reparacion;
  private int semanasSinReparacion;
  private int cantidadReparaciones;
  private int balance;

  public Iteracion(Politica politica,
      int semana,
      double rnd,
      Estado estado,
      int ingresos,
      int reparacion,
      int semanasSinReparacion,
      int cantidadReparaciones,
      int balance) {
    this.semana = semana;
    this.rnd = rnd;
    this.estado = estado;
    this.ingresos = ingresos;
    this.reparacion = reparacion;
    this.semanasSinReparacion = semanasSinReparacion;
    this.cantidadReparaciones = cantidadReparaciones;
    this.balance = balance;
    this.politica = politica;
  }

  public static Iteracion initA() {
    return new Iteracion(Politica.A,
            1,
            0.0,
            Estado.EXCELENTE,
            0,
            0,
            6,
            0,
            0);
  }

  public static Iteracion initB() {
    return new Iteracion(Politica.B,
            1,
            0.0,
            Estado.EXCELENTE,
            0,
            0,
            4,
            0,
            0);
  }

  public static Iteracion initC() {
    return new Iteracion(Politica.C,
            1,
            0.0,
            Estado.EXCELENTE,
            0,
            0,
            0,
            0,
            0);
  }

  public void nextSemana() {
    this.semana++;
  }
}
