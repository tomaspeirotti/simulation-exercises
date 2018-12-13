package com.simulation.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static final char REPLACE = 0xFEFF;
    private FileUtil() {
        //method not implemented
    }

    public static InputStreamReader getFile(String fileName) throws UnsupportedEncodingException {
        //Get file from resources folder

        ClassLoader classLoader = FileUtil.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(fileName), "UTF-8");
    }

    public static String manageUTF8BOM(StringBuilder reader) {
        if( reader.charAt(0) == REPLACE)
            reader.deleteCharAt(0);

        return reader.toString();
    }

    public static String getFileAsString(String fileName) {

        StringBuilder result = new StringBuilder("");

        try (InputStreamReader inputStream = getFile(fileName);
             BufferedReader reader = new BufferedReader(inputStream)) {

            String str;
            result = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                result.append(str);
            }

        } catch (IOException e) {
            LOGGER.warn("Error getting file: " + e.getMessage());
        }

        return manageUTF8BOM(result);
    }
}
