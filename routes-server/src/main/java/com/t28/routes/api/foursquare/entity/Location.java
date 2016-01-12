package com.t28.routes.api.foursquare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    @JsonProperty
    private String address;

    @JsonProperty
    private String crossStreet;

    @JsonProperty(required = true)
    private double lat;

    @JsonProperty(required = true, value = "lng")
    private double lon;

    @JsonProperty
    private long distance;

    @JsonProperty
    private String postalCode;

    @JsonProperty("cc")
    private String countryCode;

    @JsonProperty
    private String country;

    @JsonProperty
    private String city;

    @JsonProperty
    private String state;
}
