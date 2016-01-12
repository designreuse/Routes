package com.t28.routes.http.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * @link https://developers.google.com/maps/documentation/distance-matrix
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrix {
    private static final String STATUS_SUCCESS = "OK";

    @JsonProperty(required = true)
    private String status;

    @JsonProperty(value = "destination_addresses", required = true)
    private ArrayList<String> destinationAddresses;

    @JsonProperty(value = "origin_addresses", required = true)
    private ArrayList<String> originAddresses;

    @JsonProperty(required = true)
    private ArrayList<Elements> rows;

    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(status);
    }

    public String getStatus() {
        return status;
    }

    public String getDestinationAddresse() {
        return String.join("", destinationAddresses);
    }

    public String getOriginAddresses() {
        return String.join("", originAddresses);
    }

    public int size() {
        return rows.size();
    }

    public Elements getRowAt(int index) {
        return rows.get(index);
    }
}
