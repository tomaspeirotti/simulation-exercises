package com.simulation.service;

import com.simulation.model.clases.*;
import com.simulation.model.dto.IterationsResponseDTO;
import com.simulation.model.dto.ParametrosDTO;
import com.simulation.model.dto.SimulationResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class SimulationServiceImpl implements SimulationService {

  private static final Logger log = LoggerFactory.getLogger(SimulationServiceImpl.class);

  private List<EstadoBuilder> estados = this.initEstados();

  private ParametrosDTO params;
  private LinkedList<Iteracion> iteracionesA;
  private LinkedList<Iteracion> iteracionesB;
  private LinkedList<Iteracion> iteracionesC;
  private List<Double> randoms = new ArrayList<>();

  public SimulationServiceImpl() {}

  @Override
  public SimulationResponseDTO startSimulation(ParametrosDTO parametrosDTO) {
    this.params = parametrosDTO;

    this.randoms = this.initRandoms();

    iteracionesA = this.simulatePoliticaA();
    iteracionesB = this.simulatePoliticaB();
    iteracionesC = this.simulatePoliticaC();

    int balancePoliticaA = iteracionesA.getLast().getBalance();
    int balancePoliticaB = iteracionesB.getLast().getBalance();
    int balancePoliticaC = iteracionesC.getLast().getBalance();

    Politica politicaGanadora =
        Stream.of(iteracionesA, iteracionesB, iteracionesC)
            .max(Comparator.comparingInt(it -> it.getLast().getBalance()))
            .get()
            .getLast()
            .getPolitica();

    return SimulationResponseDTO.builder()
        .balancePoliticaA(balancePoliticaA)
        .balancePoliticaB(balancePoliticaB)
        .balancePoliticaC(balancePoliticaC)
        .politicaGanadora(politicaGanadora)
        .build();
  }

  private LinkedList<Iteracion> simulatePoliticaC() {
    LinkedList<Iteracion> iteraciones = new LinkedList<>();
    do {
      Iteracion itPasada;
      try {
        itPasada = iteraciones.getLast();
      } catch (NoSuchElementException e) {
        itPasada = Iteracion.initC();
        iteraciones.add(itPasada);
        log.info("Iteracion initialized for Politica C");
      }
      Iteracion itActual = new Iteracion();
      BeanUtils.copyProperties(itPasada, itActual);
      itActual.nextSemana();
      double rnd = this.randoms.get(itActual.getSemana() - 1);
      itActual.setRnd(rnd);
      if (itActual.getEstado().equals(Estado.MALA)) {
        itActual.setEstado(Estado.EXCELENTE);
        itActual.setReparacion(params.getCostoReparacion());
        itActual.setSemanasSinReparacion(0);
      } else {
        itActual.setEstado(this.getProximoEstado(rnd, itActual.getEstado()));
        itActual.setReparacion(0);
        itActual.setSemanasSinReparacion(itActual.getSemanasSinReparacion() + 1);
      }
      itActual.setIngresos(this.getIngresosPorEstado(itActual.getEstado()));
      itActual.setCantidadReparaciones(
          itActual.getReparacion() > 0
              ? itActual.getCantidadReparaciones() + 1
              : itActual.getCantidadReparaciones());
      itActual.setBalance(
          itActual.getBalance() + itActual.getIngresos() - itActual.getReparacion());
      iteraciones.add(itActual);
    } while (iteraciones.size() < params.getSemanas());

    return iteraciones;
  }

  private LinkedList<Iteracion> simulatePoliticaB() {
    LinkedList<Iteracion> iteraciones = new LinkedList<>();
    do {
      Iteracion itPasada;
      try {
        itPasada = iteraciones.getLast();
      } catch (NoSuchElementException e) {
        itPasada = Iteracion.initB();
        iteraciones.add(itPasada);
        log.info("Iteracion initialized for Politica B");
      }
      Iteracion itActual = new Iteracion();
      BeanUtils.copyProperties(itPasada, itActual);
      itActual.nextSemana();
      double rnd = this.randoms.get(itActual.getSemana() - 1);
      itActual.setRnd(rnd);
      if (itActual.getEstado().equals(Estado.MALA) && itActual.getSemanasSinReparacion() == 0) {
        itActual.setEstado(Estado.EXCELENTE);
        itActual.setReparacion(params.getCostoReparacion());
        itActual.setSemanasSinReparacion(4);
      } else if ((itActual.getSemanasSinReparacion() == 0
              && !itActual.getEstado().equals(Estado.MALA)
          || (itActual.getSemanasSinReparacion() > 0
              && itActual.getEstado().equals(Estado.MALA)))) {
        itActual.setEstado(this.getProximoEstado(rnd, itActual.getEstado()));
        itActual.setReparacion(0);
        itActual.setSemanasSinReparacion(
            itActual.getSemanasSinReparacion() > 0 ? itActual.getSemanasSinReparacion() - 1 : 0);
      } else {
        itActual.setEstado(this.getProximoEstado(rnd, itActual.getEstado()));
        itActual.setReparacion(0);
        itActual.setSemanasSinReparacion(itActual.getSemanasSinReparacion() - 1);
      }
      itActual.setIngresos(this.getIngresosPorEstado(itActual.getEstado()));
      itActual.setCantidadReparaciones(
          itActual.getReparacion() > 0
              ? itActual.getCantidadReparaciones() + 1
              : itActual.getCantidadReparaciones());
      itActual.setBalance(
          itActual.getBalance() + itActual.getIngresos() - itActual.getReparacion());
      iteraciones.add(itActual);
    } while (iteraciones.size() < params.getSemanas());

    return iteraciones;
  }

  private LinkedList<Iteracion> simulatePoliticaA() {
    LinkedList<Iteracion> iteraciones = new LinkedList<>();
    do {
      Iteracion itPasada;
      try {
        itPasada = iteraciones.getLast();
      } catch (Exception e) {
        itPasada = Iteracion.initA();
        iteraciones.add(itPasada);
        log.info("Iteracion initialized for Politica A");
      }
      Iteracion itActual = new Iteracion();
      BeanUtils.copyProperties(itPasada, itActual);
      itActual.nextSemana();
      itActual.setSemanasSinReparacion(
          itActual.getSemanasSinReparacion() > 0 ? itActual.getSemanasSinReparacion() - 1 : 6);
      double rnd = this.randoms.get(itActual.getSemana() - 1);
      itActual.setRnd(rnd);
      itActual.setEstado(
          itActual.getSemanasSinReparacion() == 0
              ? Estado.EXCELENTE
              : this.getProximoEstado(rnd, itActual.getEstado()));
      itActual.setIngresos(this.getIngresosPorEstado(itActual.getEstado()));
      itActual.setReparacion(
          itActual.getSemanasSinReparacion() == 0 ? params.getCostoReparacion() : 0);
      itActual.setCantidadReparaciones(
          itActual.getReparacion() > 0
              ? itActual.getCantidadReparaciones() + 1
              : itActual.getCantidadReparaciones());
      itActual.setBalance(
          itActual.getBalance() + itActual.getIngresos() - itActual.getReparacion());
      iteraciones.add(itActual);
    } while (iteraciones.size() < params.getSemanas());

    return iteraciones;
  }

  @Override
  public IterationsResponseDTO getIterations(int page, int size, Politica politica) {
    LinkedList<Iteracion> lista;
    switch (politica) {
      case A:
        lista = iteracionesA;
        break;
      case B:
        lista = iteracionesB;
        break;
      case C:
        lista = iteracionesC;
        break;
      default:
        throw new IllegalArgumentException();
    }
    if (CollectionUtils.isEmpty(lista))
      throw new IllegalArgumentException("Simulate before getting iterations!");
    return IterationsResponseDTO.builder()
        .iteraciones(ListUtils.partition(lista, size).get(page))
        .totalRows(lista.size())
        .build();
  }

  private List<EstadoBuilder> initEstados() {
    List<EstadoBuilder> estados = new ArrayList<>();

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.EXCELENTE)
            .limites(new DoubleTuple(0.00, 0.7))
            .posibleProximoEstado(Estado.EXCELENTE)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.EXCELENTE)
            .limites(new DoubleTuple(0.7, 0.9))
            .posibleProximoEstado(Estado.BUENA)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.EXCELENTE)
            .limites(new DoubleTuple(0.9, 1.0))
            .posibleProximoEstado(Estado.MALA)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.BUENA)
            .limites(new DoubleTuple(0.0, 0.0))
            .posibleProximoEstado(Estado.EXCELENTE)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.BUENA)
            .limites(new DoubleTuple(0.0, 0.7))
            .posibleProximoEstado(Estado.BUENA)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.BUENA)
            .limites(new DoubleTuple(0.7, 1.0))
            .posibleProximoEstado(Estado.MALA)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.MALA)
            .limites(new DoubleTuple(0.0, 0.0))
            .posibleProximoEstado(Estado.EXCELENTE)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.MALA)
            .limites(new DoubleTuple(0.0, 0.1))
            .posibleProximoEstado(Estado.BUENA)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.MALA)
            .limites(new DoubleTuple(0.1, 1.0))
            .posibleProximoEstado(Estado.MALA)
            .build());

    return estados;
  }

  private Estado getProximoEstado(double rnd, Estado actual) {
    //    List<EstadoBuilder> estadosList = estados.stream()
    //            .filter(estado -> estado.getActual().equals(actual))
    //            .filter(estado -> estado.getLimites().getX2() > rnd && estado.getLimites().getX1()
    // <= rnd)
    //            .collect(Collectors.toList());
    //
    //    if (estadosList.size() > 1) {
    //      log.warn("Estado Actual [{}] - Returned two states for one random: [{}] [{}]", actual,
    // rnd, estadosList);
    //    } else if (estadosList.isEmpty()) {
    //      log.error("No state found for rnd [{}] - Actual [{}]", rnd, actual);
    //    }

    return estados.stream()
        .filter(estado -> estado.getActual().equals(actual))
        .filter(estado -> estado.getLimites().getX2() > rnd && estado.getLimites().getX1() <= rnd)
        .findAny()
        .orElseThrow(IllegalStateException::new)
        .getPosibleProximoEstado();
  }

  private int getIngresosPorEstado(Estado estado) {
    switch (estado) {
      case MALA:
        return params.getIngresosMalo();
      case BUENA:
        return params.getIngresosBueno();
      case EXCELENTE:
        return params.getIngresosExcelente();
      default:
        return 0;
    }
  }

  private double getRandom() {
    Random r = new Random();
    int low = 0;
    int high = 99;
    return roundAvoid((r.nextInt(high - low) + low) * 0.01, 2);
  }

  private static double roundAvoid(double value, int places) {
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
  }

  private List<Double> initRandoms() {
    List<Double> randoms = new ArrayList<>();
    for (int i = 0; i < params.getSemanas(); i++) {
      randoms.add(this.getRandom());
    }
    return randoms;
  }
}
