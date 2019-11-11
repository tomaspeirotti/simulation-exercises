package com.simulation.service;

import com.simulation.model.clases.*;
import com.simulation.model.dto.IterationsResponseDTO;
import com.simulation.model.dto.ParametrosDTO;
import com.simulation.model.dto.SimulationResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  public SimulationServiceImpl() {}

  @Override
  public SimulationResponseDTO startSimulation(ParametrosDTO parametrosDTO) {
    this.params = parametrosDTO;

    iteracionesA = this.simulatePolitica(Politica.A);
    iteracionesB = this.simulatePolitica(Politica.B);
    iteracionesC = this.simulatePolitica(Politica.C);

    int balancePoliticaA = iteracionesA.getLast().getBalance();
    int balancePoliticaB = iteracionesB.getLast().getBalance();
    int balancePoliticaC = iteracionesC.getLast().getBalance();

    Politica politicaGanadora = Stream.of(iteracionesA, iteracionesB, iteracionesC)
            .max(Comparator.comparingInt(it -> it.getLast().getBalance()))
            .get().getLast().getPolitica();

    return SimulationResponseDTO.builder()
            .balancePoliticaA(balancePoliticaA)
            .balancePoliticaB(balancePoliticaB)
            .balancePoliticaC(balancePoliticaC)
            .politicaGanadora(politicaGanadora)
            .build();
  }

  private LinkedList<Iteracion> simulatePolitica(Politica politica) {
    switch (politica) {
      case A:
        return this.simulatePoliticaA();
      case B:
        return this.simulatePoliticaB();
      case C:
        return this.simulatePoliticaC();
      default:
        throw new IllegalArgumentException();
    }
  }

  private LinkedList<Iteracion> simulatePoliticaC() {
    return null;
  }

  private LinkedList<Iteracion> simulatePoliticaB() {
    return null;
  }

  private LinkedList<Iteracion> simulatePoliticaA() {
    return null;
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
            .limites(new DoubleTuple(0.00, 0.69))
            .posibleProximoEstado(Estado.EXCELENTE)
            .build());

    estados.add(
        EstadoBuilder.builder()
            .actual(Estado.EXCELENTE)
            .limites(new DoubleTuple(0.7, 0.89))
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
            .limites(new DoubleTuple(0.0, 0.69))
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
            .limites(new DoubleTuple(0.0, 0.09))
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
    return estados.stream()
        .filter(estado -> estado.getActual().equals(actual))
        .filter(estado -> estado.getLimites().getX1() >= rnd && estado.getLimites().getX2() <= rnd)
        .findAny()
        .orElseThrow(IllegalStateException::new)
        .getPosibleProximoEstado();
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
}
