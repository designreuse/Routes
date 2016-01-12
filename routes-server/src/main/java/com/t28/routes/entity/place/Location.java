package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty(required = true)
    private final String country;

    @JsonProperty(required = true)
    private final String countryCode;

    @JsonProperty(required = true)
    private final String state;

    @JsonProperty(required = true)
    private final String address;

    @JsonProperty(required = true)
    private final Coordinate coordinate;

    private Location(Builder builder) {
        country = builder.country;
        countryCode = builder.countryCode;
        state = builder.state;
        address = builder.address;
        coordinate = builder.coordinate;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public static class Builder {
        private String country;
        private String countryCode;
        private String state;
        private String address;
        private Coordinate coordinate;

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder coordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
