package com.simulacion.clases;

import com.simulacion.dto.ParametrosDTO;
import com.simulacion.util.JSONUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSVBusiness {

    private static final String DESKTOP_DIRECTORY = Paths.get("C:\\Users\\Tomas\\Desktop").toString();
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVBusiness.class);

    public void printCSV(List<Iteracion> iteraciones, List<String> columnas) throws IOException {
        Path folderPath = createTempFolder("Simulacion");
        Path csvFilePath = Paths.get(folderPath.toString(), "records"+ LocalTime.now().toSecondOfDay() +".csv");
        csvFilePath = Files.createFile(csvFilePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            csvPrinter.printRecord(columnas);
            for (Iteracion iteracion: iteraciones) csvPrinter.printRecord(buildRecord(iteracion));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private List<String> buildRecord(Iteracion iteracion) {
        List<String> record = new ArrayList<>();
        Empleado emp0 = iteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==0).findFirst().get();
        Empleado emp1 = iteracion.getEmpleados().stream().filter(emp -> emp.getNumero()==1).findFirst().get();
        record.add(iteracion.getTiempo().toString());
        record.add(iteracion.getFrecArrivos());
        record.add(iteracion.getEvento().getTipo().toString());
        record.add(String.valueOf(iteracion.getRandom()));
        record.add(iteracion.getProxArrivo().toString());
        record.add(emp0.getEstadoEmpleado().toString());
        record.add(emp0.getFinAtencion().toString());
        record.add(String.valueOf(emp0.getCola()));
        record.add(emp1.getEstadoEmpleado().toString());
        record.add(emp1.getFinAtencion().toString());
        record.add(String.valueOf(emp1.getCola()));
        iteracion.getClientes().stream()
                .sorted(Comparator.comparing(Cliente::getNroCliente)) //TODO: Verify sort
                .forEach(cliente -> {
                    record.add(cliente.getEstado().toString());
                    record.add(cliente.getSiendoAtendidoPor().getNombre());
                });
        return record;
    }

    private Path createTempFolder(String folderName) throws IOException {
        Path tempFolderPath = Paths.get(DESKTOP_DIRECTORY, folderName);
        tempFolderPath = Files.createDirectories(tempFolderPath);
        return tempFolderPath;
    }

}
