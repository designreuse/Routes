package com.t28.routes.http.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Distance {
    @JsonProperty(required = true)
    private String text;

    @JsonProperty(required = true)
    private int value;

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
