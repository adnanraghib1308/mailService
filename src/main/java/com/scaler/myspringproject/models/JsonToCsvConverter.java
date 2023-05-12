package com.scaler.myspringproject.models; 

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonToCsvConverter {
    
    public static byte[] convertToCsv(List<Map<String, Object>> jsonData) throws IOException {
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        ObjectMapper objectMapper = new ObjectMapper();
        CsvMapper csvMapper = new CsvMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        // Get the column names from the first JSON object
        if (jsonData != null && !jsonData.isEmpty()) {
            Map<String, Object> firstObject = jsonData.get(0);
            firstObject.keySet().forEach(columnName -> {
                schemaBuilder.addColumn(columnName);
            });
        }
        
        CsvSchema csvSchema = schemaBuilder.build().withHeader();
        MappingIterator<Map<String, Object>> mappingIterator = objectMapper
            .readerFor(Map.class)
            .with(csvSchema)
            .readValues(objectMapper.writeValueAsString(jsonData));
        
        // Write the CSV data to a byte array output stream
        csvMapper.writer().with(csvSchema).writeValues(byteArrayOutputStream).write(mappingIterator);
        
        return byteArrayOutputStream.toByteArray();
    }
}
