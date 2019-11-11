package com.simulation.service;

import com.simulation.model.clases.*;
import com.simulation.model.dto.ParametrosDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Stream;

@Service
public class SimulationServiceImpl implements SimulationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimulationServiceImpl.class);

  private List<DoubleTuple> plazos = this.initPlazos();
  private List<DoubleTuple> demandas = this.initDemandas();
  private ParametrosDTO params;
  private LinkedList<Iteracion> iteraciones;
  private LinkedList<Ks> proxCobrosKs = new LinkedList<>();

  public SimulationServiceImpl() {}

  @Override
  public SimulationResponseDTO startSimulation(ParametrosDTO parametrosDTO) {
    this.params = parametrosDTO;
    iteraciones = new LinkedList<>();
    proxCobrosKs = new LinkedList<>();
    if (params.getDiasParaReponer() > 0 && params.getNivelReposicion() > 0) {
      throw new IllegalArgumentException(
          "Parametros invalidos, dias para reponer y nivel de reposicion mayores a cero");
    } else if (params.getDiasParaReponer() == 0 && params.getNivelReposicion() == 0) {
      throw new IllegalArgumentException(
          "Parametros invalidos, dias para reponer y nivel de reposicion iguales a cero");
    }
    for (int i = 1; i < parametrosDTO.getDiasDeOperacion(); i++) {

      Iteracion itActual = new Iteracion();
      Iteracion itPrevia;

      if (i > 1) {
        itPrevia = iteraciones.getLast();
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

      if (i == 1) {
        itActual.setCostoAcumulado(itActual.getCostoTotal());
      } else {
        itActual.setCostoAcumulado(itActual.getCostoTotal() + itPrevia.getCostoAcumulado());
      }

      iteraciones.add(itActual);
    }
    return SimulationResponseDTO.builder()
        .costoPromedioPorDia(iteraciones.getLast().getCostoAcumulado() / iteraciones.size())
        .totalAcumulado(iteraciones.getLast().getCostoAcumulado())
        .build();
  }

  private Ks buildKs(Iteracion itActual) {
    Ks ksAMostrar = new Ks();
    Ks ksAGuardar = new Ks();
    Demanda demandaActual = itActual.getDemanda();

    if ((demandaActual.getStock() < 0 && demandaActual.getCantidad() > 0)) {
      ksAGuardar.setDiaDeuda(String.valueOf(itActual.getDia()));
      ksAGuardar.setKs(Math.abs(demandaActual.getStock() * params.getCostoStockout()));
      int proxCobro = itActual.getDia() + params.getDiasParaPagarStockOut();
      ksAGuardar.setProxCobro(String.valueOf(proxCobro));
      ksAGuardar.setDeudasPendientes(proxCobrosKs.size() + 1);
      proxCobrosKs.add(ksAGuardar);
    }

    Optional<Ks> posibleKsACobrar = getProxKs();
    if (posibleKsACobrar.isPresent()) {
      ksAMostrar.setProxCobro(posibleKsACobrar.get().getProxCobro());
      ksAMostrar.setDiaDeuda(posibleKsACobrar.get().getDiaDeuda());
      if (posibleKsACobrar.get().getProxCobro().equals(String.valueOf(itActual.getDia()))) {
        ksAMostrar.setKs(posibleKsACobrar.get().getKs());
        proxCobrosKs.remove(posibleKsACobrar.get());
      }
      ksAMostrar.setDeudasPendientes(proxCobrosKs.size());
    }

    return ksAMostrar;
  }

  private Optional<Ks> getProxKs() {
    return proxCobrosKs.stream().min(Comparator.comparing(Ks::getProxCobro));
  }

  private double buildKo(Iteracion itActual) {
    return !itActual.getPedido().getRnd().isEmpty() ? params.getCostoOrdenamiento() : 0;
  }

  private double buildKm(Iteracion itActual) {
    if (itActual.getDemanda().getStock() > 0) {
      return itActual.getDemanda().getStock() * params.getCostoMantenimiento();
    } else {
      return 0D;
    }
  }

  private Demanda buildDemanda(Iteracion itPrevia, int dia) {
    Demanda dm = new Demanda();
    if (itPrevia != null) BeanUtils.copyProperties(itPrevia.getDemanda(), dm);
    if (dia == 1) dm.setStock(params.getStockInicial());
    double rnd = getRandom();
    dm.setCantidad(getDemanda(rnd));
    dm.setRnd(String.valueOf(rnd));
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
      pedido.setRnd("");
      if (isPedidoNecesario(itPrevia, itActual)) {
        pedido.setEnCurso(true);
        if (params.getNivelReposicion() > 0) {
          double rnd = getRandom();
          pedido.setRnd(String.valueOf(rnd));
          pedido.setPlazoDeReposicion(String.valueOf(getPlazoDeReposicion(rnd)));
          pedido.setDiaDeResposicion(
              String.valueOf(Integer.parseInt(pedido.getPlazoDeReposicion()) + itActual.getDia()));
        } else {
          pedido.setPlazoDeReposicion(String.valueOf(params.getDiasParaReponer()));
          pedido.setDiaDeResposicion(
              String.valueOf(Integer.parseInt(pedido.getPlazoDeReposicion()) + itActual.getDia()));
        }
        return pedido;
      } else if (llegoPedido(itPrevia, itActual)) {
        itActual
            .getDemanda()
            .setStock(itPrevia.getDemanda().getStock() + params.getCantReposicion());
        pedido.setUltimaReposicion(String.valueOf(itActual.getDia()));
        pedido.setPlazoDeReposicion("");
        pedido.setDiaDeResposicion("");
        pedido.setEnCurso(false);
        return pedido;
      } else { // En curso
        pedido.setPlazoDeReposicion("");
        return pedido;
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private boolean llegoPedido(Iteracion itPrevia, Iteracion itActual) {
    int diaDeResposicion;
    if (itPrevia != null
        && itPrevia.getPedido().getDiaDeResposicion() != null
        && !itPrevia.getPedido().getDiaDeResposicion().isEmpty()) {
      diaDeResposicion = Integer.parseInt(itPrevia.getPedido().getDiaDeResposicion());
    } else {
      return false;
    }
    return itActual.getDia() == diaDeResposicion;
  }

  private boolean isPedidoNecesario(Iteracion itPrevia, Iteracion itActual) {
    if (itActual.getDia() == 1) return true;
    if (itPrevia != null && itPrevia.getPedido().isEnCurso()) return false;

    if (params.getDiasParaReponer() > 0) {
      int ultimaReposicion;
      if (itPrevia == null) {
        ultimaReposicion = 0;
      } else if (!StringUtils.isEmpty(itPrevia.getPedido().getUltimaReposicion())) {
        ultimaReposicion = Integer.parseInt(itPrevia.getPedido().getUltimaReposicion());
      } else {
        ultimaReposicion = 0;
      }
      return (params.getDiasParaReponer() < (itActual.getDia() - ultimaReposicion));

    } else if (params.getNivelReposicion() > 0) {
      return (itActual.getDemanda().getStock() <= params.getNivelReposicion());
    } else {
      return false;
    }
  }

  @Override
  public IterationsResponseDTO getIterations(int page, int size) {
    if (CollectionUtils.isEmpty(iteraciones))
      throw new IllegalArgumentException("Simulate before getting iterations!");
    return IterationsResponseDTO.builder()
        .iteraciones(ListUtils.partition(iteraciones, size).get(page))
        .totalRows(iteraciones.size())
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
        plazo = plazos.indexOf(tuple) + 1; // TODO: CHECK
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
