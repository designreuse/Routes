package com.t28.routes.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Entity {
    private final ObjectMapper objectMapper;

    protected Entity() {
        this(new ObjectMapper());
    }

    protected Entity(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(e);
        }
    }
}
