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
import java.util.List;

public class SimulationBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationBusiness.class);

    private static final String DESKTOP_DIRECTORY = Paths.get("C:\\Users\\Tomas\\Desktop").toString();
    private static final DateTimeFormatter csvDataDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private JSONUtils<ParametrosDTO> parametrosDTOJSONUtils;


    public ParametrosDTO getParametrosFromJson() throws IOException {
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

    private List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) values.add(String.valueOf(LocalTime.now().toNanoOfDay()));
        return values;
    }

    public void printCSV() throws IOException {
        Path folderPath = createTempFolder("Simulacion");
        Path csvFilePath = Paths.get(folderPath.toString(), "records"+ LocalTime.now().toSecondOfDay() +".csv");
        csvFilePath = Files.createFile(csvFilePath);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(csvFilePath);
            CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (int i = 0; i <= 10000; i++) csvPrinter.printRecord(getValues());
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
