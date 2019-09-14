package com.simulation.service;

import com.simulation.model.clases.DoubleTuple;
import com.simulation.model.clases.Iteracion;
import com.simulation.model.dto.ParametrosDTO;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class SimulationServiceImpl implements SimulationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimulationServiceImpl.class);

  private List<DoubleTuple> plazos = this.initPlazos();
  private List<DoubleTuple> demandas = this.initDemandas();
  private ParametrosDTO params;
  private LinkedList<Iteracion> iteraciones;

  public SimulationServiceImpl() {
  }

  @Override
  public void startSimulation(ParametrosDTO parametrosDTO) {
    this.params = parametrosDTO;
    iteraciones = new LinkedList<>();
    for (Long i = 0L; i < parametrosDTO.getDiasDeOperacion(); i++) {
      Iteracion it = new Iteracion();
      it.setDia(i);
      iteraciones.add(it);
    }
  }

  @Override
  public List<Iteracion> getIterations(int page, int size) {
    return ListUtils.partition(iteraciones, size).get(page);
  }

  private List<DoubleTuple> initPlazos() {
    List<DoubleTuple> plazos = new LinkedList<>();
    plazos.add(new DoubleTuple(0D, 0.09));
    plazos.add(new DoubleTuple(0.1, 0.29));
    plazos.add(new DoubleTuple(0.3, 0.59));
    plazos.add(new DoubleTuple(0.6, 0.89));
    plazos.add(new DoubleTuple(0.9, 0.94));
    plazos.add(new DoubleTuple(0.95, 0.99));
    return plazos;
  }

  private int getPlazoDeReposicion() {
    Double rnd = getRandom();
    int plazo = -1;
    for (DoubleTuple tuple : this.plazos) {
      if (rnd >= tuple.getX1() && rnd <= tuple.getX2()) {
        plazo = plazos.indexOf(tuple);
      }
    }
    if (plazo == -1) {
      throw new IllegalArgumentException();
    } else {
      return plazo;
    }
  }

  private List<DoubleTuple> initDemandas() {
    List<DoubleTuple> demandas = new LinkedList<>();
    demandas.add(new DoubleTuple(0D, 0.049));
    demandas.add(new DoubleTuple(0.05, 0.24));
    demandas.add(new DoubleTuple(0.25, 0.64));
    demandas.add(new DoubleTuple(0.65, 0.79));
    demandas.add(new DoubleTuple(0.8, 0.89));
    demandas.add(new DoubleTuple(0.9, 0.94));
    demandas.add(new DoubleTuple(0.95, 0.99));
    return demandas;
  }

  private int getDemanda() {
    Double rnd = getRandom();
    int demanda = -1;
    for (DoubleTuple tuple : this.demandas) {
      if (rnd >= tuple.getX1() && rnd <= tuple.getX2()) {
        demanda = demandas.indexOf(tuple);
      }
    }
    if (demanda == -1) {
      throw new IllegalArgumentException();
    } else {
      return demanda;
    }
  }

  private double getRandom() {
    Random r = new Random();
    int low = 0;
    int high = 99;
    return (r.nextInt(high - low) + low) * 0.01;
  }
}
