package com.t28.routes.api.unirest;

import com.google.common.annotations.VisibleForTesting;
import com.mashape.unirest.http.ObjectMapper;

import java.io.IOException;

public class JacksonMapper implements ObjectMapper {
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    public JacksonMapper() {
        this(new com.fasterxml.jackson.databind.ObjectMapper());
    }

    @VisibleForTesting
    JacksonMapper(com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return objectMapper.readValue(value, valueType);
        } catch (IOException e) {
            throw new JacksonProcessingException("Failed to read value:" + value, e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new JacksonProcessingException("Failed to write value:" + value, e);
        }
    }
}
