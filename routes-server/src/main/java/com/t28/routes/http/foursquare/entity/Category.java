package com.t28.routes.http.foursquare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Category {
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String pluralName;

    @JsonProperty
    private String shortName;

    @JsonProperty("primary")
    private boolean isPrimary;

    @JsonProperty
    private Map<String, String> icon;
}
