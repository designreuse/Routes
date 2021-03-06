package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Coordinate;
import com.t28.routes.entity.Entity;

public class Location extends Entity {
    @JsonProperty(required = true)
    private String country;

    @JsonProperty(required = true)
    private String countryCode;

    @JsonProperty(required = true)
    private String state;

    @JsonProperty
    private String city;

    @JsonProperty(required = true)
    private String address;

    @JsonProperty(required = true)
    private Coordinate coordinate;

    public Location() {
    }

    private Location(Builder builder) {
        super();
        country = builder.country;
        countryCode = builder.countryCode;
        state = builder.state;
        city = builder.city;
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

    public String getCity() {
        return city;
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
        private String city;
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

        public Builder city(String city) {
            this.city = city;
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
