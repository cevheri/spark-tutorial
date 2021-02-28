package org.cevher.spark;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class JsonParserJava implements Serializable {
    public static IncomingJava parseJson(String line) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(line, IncomingJava.class);
    }
}