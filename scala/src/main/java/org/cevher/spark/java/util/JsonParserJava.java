package org.cevher.spark.java.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cevher.spark.java.domain.IncomingJava;

import java.io.Serializable;

public class JsonParserJava implements Serializable {
    public static IncomingJava parseJson(String line) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(line, IncomingJava.class);
    }
}