package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
    @JsonProperty(required = true)
    private final double lat;

    @JsonProperty(required = true)
    private final double lon;

    private Coordinate(Builder builder) {
        lat = builder.lat;
        lon = builder.lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public static class Builder {
        private double lat;
        private double lon;

        public Builder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public Builder lon(double lon) {
            this.lon = lon;
            return this;
        }

        public Coordinate build() {
            return new Coordinate(this);
        }
    }
}
