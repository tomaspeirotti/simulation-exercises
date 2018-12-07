package com.simulacion.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by simtlix on 2/1/2018.
 */
public class JSONUtils<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    public static String convertObjectToJson(Object object){
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.error("Error to try serialize to JSON",e);
        }
        return json;
    }

    public T convertJsonToObject(String json,Class<T> clazz){
        T result = null;
        try {
            result = new ObjectMapper().readValue(json,clazz);
        } catch (IOException e) {
            LOGGER.error("Error trying to parse " +json+ " to JSON", e);
        }
        return result;
    }
}
