package com.simulation.model.clases;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVBusiness {

    private static final String DESKTOP_DIRECTORY = Paths.get("C:\\Users\\Tomas\\Desktop").toString();
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVBusiness.class);

    public void printCSV(List<Iteracion> iteraciones, List<String> columnas, boolean agregarColumnasClientes) throws IOException {
        Path folderPath = createTempFolder("Simulacion");
        Path csvFilePath = Paths.get(folderPath.toString(), "records"+ LocalTime.now().toSecondOfDay() +".csv");
        csvFilePath = Files.createFile(csvFilePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.EXCEL.withFirstRecordAsHeader())) {
            if (agregarColumnasClientes) {
                for (Cliente cliente: iteraciones.get(iteraciones.size()-1).getClientes().stream().sorted(Comparator.comparing(Cliente::getNroCliente)).collect(Collectors.toList())) {
                    columnas.add("C" + cliente.getNroCliente() + ":Estado");
                    columnas.add("C" + cliente.getNroCliente() + ":Emp");
                }
            }
            csvPrinter.printRecord(columnas);

            for (Iteracion iteracion: iteraciones) csvPrinter.printRecord(buildRecord(iteracion, agregarColumnasClientes));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

    public List<String> buildRecord(Iteracion iteracion, boolean agregarClientes) {
        List<String> record = new ArrayList<>();
        Empleado emp0 = iteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==0).findFirst().get();
        Empleado emp1 = iteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==1).findFirst().get();
        record.add(iteracion.getTiempo().toString());
        record.add(iteracion.getFrecArrivos());
        record.add(iteracion.getEvento().getTipo().toString());
        record.add(new DecimalFormat("##.##").format(iteracion.getRandom1()));
        record.add(new DecimalFormat("##.##").format(iteracion.getRandom2()));
        record.add(iteracion.getProxArrivo().toString());
        record.add(iteracion.getTiempoEntreArrivos() != null ? iteracion.getTiempoEntreArrivos().toString() : null);
        record.add(emp0.getEstadoEmpleado().toString());
        record.add(emp0.getFinAtencion()==null?"":emp0.getFinAtencion().toString());
        record.add(String.valueOf(emp0.getColaSize(iteracion.getClientes())));
        record.add(emp1.getEstadoEmpleado().toString());
        record.add(emp1.getFinAtencion()==null?"":emp1.getFinAtencion().toString());
        record.add(String.valueOf(emp1.getColaSize(iteracion.getClientes())));
        record.add(String.valueOf(iteracion.getLongMaximaCola()));
        if (agregarClientes) {
            iteracion.getClientes().stream()
                    .sorted(Comparator.comparing(Cliente::getNroCliente))
                    .forEach(cliente -> {
                        record.add(cliente.getEstado()!=null?cliente.getEstado().toString():"");
                        record.add(String.valueOf(cliente.getSiendoAtendidoPor()==null?"":cliente.getSiendoAtendidoPor().getNumero()));
                    });
        }
        return record;
    }

    private Path createTempFolder(String folderName) throws IOException {
        Path tempFolderPath = Paths.get(DESKTOP_DIRECTORY, folderName);
        tempFolderPath = Files.createDirectories(tempFolderPath);
        return tempFolderPath;
    }

}
