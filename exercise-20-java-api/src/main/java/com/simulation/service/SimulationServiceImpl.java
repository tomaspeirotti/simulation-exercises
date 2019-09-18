package com.simulation.service;

import com.simulation.model.clases.*;
import com.simulation.model.dto.ParametrosDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

  public SimulationServiceImpl() {}

  @Override
  public void startSimulation(ParametrosDTO parametrosDTO) {
    this.params = parametrosDTO;
    iteraciones = new LinkedList<>();
    for (int i = 0; i < parametrosDTO.getDiasDeOperacion(); i++) {

      Iteracion itActual = new Iteracion();
      Iteracion itPrevia;

      if (i > 0) {
        itPrevia = iteraciones.get(i - 1);
      } else {
        itPrevia = null;
      }

      itActual.setDia(i);

      Demanda demanda = buildDemanda(itPrevia, i);
      itActual.setDemanda(demanda);

      Pedido pedido = buildPedido(itPrevia, itActual);
      itActual.setPedido(pedido);

      double km = buildKm(itActual);
      itActual.setKm(km);

      double ko = buildKo(itActual);
      itActual.setKo(ko);

      Ks ks = buildKs(itActual);
      itActual.setKs(ks);

      itActual.setCostoTotal(km + ko + ks.getKs());
      if (i == 0) {
        itActual.setCostoAcumulado(0);
      } else {
        itActual.setCostoAcumulado(itPrevia.getCostoTotal() + itActual.getCostoTotal());
      }

      iteraciones.add(itActual);
    }
  }

  private Ks buildKs(Iteracion itActual) {
    Ks ks = new Ks();
    Demanda demandaActual = itActual.getDemanda();
    if (demandaActual.getStock() <= 0 && demandaActual.getCantidad() > 0) {
      ks.setKs(demandaActual.getStock() * params.getCostoStockout());
      ks.setDiaDeCobro(itActual.getDia() + params.getDiasParaPagarStockOut());
    }
    return ks;
  }

  private double buildKo(Iteracion itActual) {
    return itActual.getPedido().getRnd() > 0 ? params.getCostoOrdenamiento() : 0;
  }

  private double buildKm(Iteracion itActual) {
    return itActual.getDemanda().getStock() * params.getCostoMantenimiento();
  }

  private Demanda buildDemanda(Iteracion itPrevia, int dia) {
    Demanda dm = new Demanda();
    if (itPrevia != null) BeanUtils.copyProperties(itPrevia.getDemanda(), dm);
    if (dia == 0) dm.setStock(params.getStockInicial());
    double rnd = getRandom();
    dm.setCantidad(getDemanda(rnd));
    dm.setRnd(rnd);
    if (itPrevia != null) {
      dm.setStock(dm.getStock() - itPrevia.getDemanda().getCantidad());
    }
    return dm;
  }

  private Pedido buildPedido(Iteracion itPrevia, Iteracion itActual) {
    Pedido pedido = new Pedido();
    if (itPrevia != null) {
      BeanUtils.copyProperties(itPrevia.getPedido(), pedido);
    }
    try {
      pedido.setRnd(-1);
      if (isPedidoNecesario(itPrevia, itActual)) {
        double rnd = getRandom();
        pedido.setRnd(rnd);
        pedido.setPlazoDeReposicion(getPlazoDeReposicion(rnd));
        pedido.setDiaDeResposicion(pedido.getPlazoDeReposicion() + itActual.getDia());
        return pedido;
      } else if (llegoPedido(itActual)) {
        itActual
            .getDemanda()
            .setStock(itPrevia.getDemanda().getStock() + params.getCantReposicion());
        pedido.setUltimaReposicion(itActual.getDia());
        return pedido;
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
    if (itPrevia != null) pedido = itPrevia.getPedido();
    return pedido;
  }

  private boolean llegoPedido(Iteracion itActual) {
    int diaDeResposicion;
    if (itActual.getPedido() != null) {
      diaDeResposicion = itActual.getPedido().getDiaDeResposicion();
    } else {
      return false;
    }
    return itActual.getDia() == diaDeResposicion;
  }

  private boolean isPedidoNecesario(Iteracion itPrevia, Iteracion itActual) {
    if (itActual.getDia() == 0) return true;
    if (params.getDiasParaReponer() > 0 && params.getNivelReposicion() > 0)
      throw new IllegalArgumentException(
          "Parametros invalidos, dias para reponer y nivel de reposicion > 0");

    int ultimaReposicion;

    if (itPrevia == null) {
      ultimaReposicion = 0;
    } else {
      ultimaReposicion = itPrevia.getPedido().getUltimaReposicion();
    }

    return ((params.getDiasParaReponer() > 0
            && params.getNivelReposicion() == 0
            && params.getDiasParaReponer() < (itActual.getDia() - ultimaReposicion))
        || (params.getDiasParaReponer() == 0
            && params.getNivelReposicion() > 0
            && itActual.getDemanda().getStock() <= params.getNivelReposicion()));
  }

  @Override
  public IterationsResponseDTO getIterations(int page, int size) {
    if (CollectionUtils.isEmpty(iteraciones))
      throw new IllegalArgumentException("Simulate before getting iterations!");
    return IterationsResponseDTO.builder()
        .iteraciones(ListUtils.partition(iteraciones, size).get(page))
        .totalAcumulado(iteraciones.getLast().getCostoAcumulado())
        .costoPromedioPorDia(iteraciones.getLast().getCostoAcumulado() / iteraciones.size())
        .build();
  }

  private List<DoubleTuple> initPlazos() {
    List<DoubleTuple> plazos = new LinkedList<>();
    plazos.add(new DoubleTuple(0.00, 0.09));
    plazos.add(new DoubleTuple(0.1, 0.29));
    plazos.add(new DoubleTuple(0.3, 0.59));
    plazos.add(new DoubleTuple(0.6, 0.89));
    plazos.add(new DoubleTuple(0.9, 0.94));
    plazos.add(new DoubleTuple(0.95, 0.99));
    return plazos;
  }

  private int getPlazoDeReposicion(Double rnd) {
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

  private int getDemanda(Double rnd) {
    int demanda = -1;
    for (DoubleTuple tuple : this.demandas) {
      if (rnd >= tuple.getX1() && rnd <= tuple.getX2()) {
        demanda = demandas.indexOf(tuple);
      }
    }
    if (demanda == -1) {
      throw new IllegalArgumentException("");
    } else {
      return demanda;
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
}
