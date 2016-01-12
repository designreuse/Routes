package com.t28.routes.http.foursquare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TinyVenue {
    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private Location location;

    @JsonProperty(required = true)
    private List<Category> categories;

    @JsonProperty(required = true, value = "verified")
    private boolean isVerified;
}
