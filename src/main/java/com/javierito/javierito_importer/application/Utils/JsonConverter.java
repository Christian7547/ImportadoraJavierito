package com.javierito.javierito_importer.application.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public JsonConverter() {
        this.objectMapper = new ObjectMapper();
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String serializeObject(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T deserializeObject(String json, Class<T> target) throws JsonProcessingException {
        return objectMapper.readValue(json, target);
    }

    public String serializeCollection(Collection<?> target) throws JsonProcessingException {
        return objectMapper.writeValueAsString(target);
    }

    public <T> List<T> deserializeCollection(String json, Class<T> elementType) throws JsonProcessingException {
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, elementType);
        return objectMapper.readValue(json, type);
    }
}
