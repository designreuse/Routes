package com.t28.routes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectId {
    @JsonProperty("$oid")
    private String value;

    public String getValue() {
        return value;
    }
}
