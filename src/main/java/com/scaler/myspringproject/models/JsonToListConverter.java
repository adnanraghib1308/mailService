package com.scaler.myspringproject.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonToListConverter {
    
    public static List<Map<String, Object>> convertToList(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, List.class);
    }
}
