package com.t28.routes.http.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Element extends Entity {
    private static final String STATUS_SUCCESS = "OK";

    @JsonProperty
    private String status;

    @JsonProperty
    private Distance distance;

    @JsonProperty
    private Duration duration;

    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(status);
    }

    public String getStatus() {
        return status;
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }
}
