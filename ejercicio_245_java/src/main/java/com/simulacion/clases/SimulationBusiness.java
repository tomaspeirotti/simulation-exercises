package com.simulacion.clases;

import com.simulacion.dto.ParametrosDTO;
import com.simulacion.util.FileUtil;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationBusiness.class);

    private static final String DESKTOP_DIRECTORY = Paths.get("C:\\Users\\Tomas\\Desktop").toString();
    private static final DateTimeFormatter csvDataDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private JSONUtils<ParametrosDTO> parametrosDTOJSONUtils;

    public ParametrosDTO getParametrosFromJson() {
        String json = FileUtil.getFileAsString("parametros.json");
        return getParametrosDTOJSONUtils().convertJsonToObject(json, ParametrosDTO.class);
    }

    public JSONUtils<ParametrosDTO> getParametrosDTOJSONUtils() {
        if (parametrosDTOJSONUtils == null) {
            setParametrosDTOJSONUtils(new JSONUtils<>());
        }
        return parametrosDTOJSONUtils;
    }

    public void setParametrosDTOJSONUtils(JSONUtils<ParametrosDTO> parametrosDTOJSONUtils) {
        this.parametrosDTOJSONUtils = parametrosDTOJSONUtils;
    }

    private List<String> getcolumnasPredeterminadas() {
        String[] columnasPredeterminadas = {"Tiempo","Frec de arrivos","Evento","RND","Prox arrivo","Emp 1 - Estado","Emp 1 - Tiempo fin At","Emp 1 - Cola","Emp 2 - Estado", "Emp 2 - Tiempo fin At", "Emp 2 - Cola"};
        return new ArrayList<>(Arrays.asList(columnasPredeterminadas).subList(0, columnasPredeterminadas.length));
    }

    public void printCSV() throws IOException {
        Path folderPath = createTempFolder("Simulacion");
        Path csvFilePath = Paths.get(folderPath.toString(), "records"+ LocalTime.now().toSecondOfDay() +".csv");
        csvFilePath = Files.createFile(csvFilePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(csvFilePath);
            CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            csvPrinter.printRecord(getcolumnasPredeterminadas());
            for (int i = 0; i <= 10; i++) csvPrinter.printRecord(i);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Path createTempFolder(String folderName) throws IOException {
        Path tempFolderPath = Paths.get(DESKTOP_DIRECTORY, folderName);
        tempFolderPath = Files.createDirectories(tempFolderPath);
        return tempFolderPath;
    }
}
