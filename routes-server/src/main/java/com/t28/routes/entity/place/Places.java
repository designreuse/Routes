package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Places {
    @JsonProperty(required = true)
    private final List<Place> places;

    public Places(List<Place> places) {
        this.places = new ArrayList<Place>(places);
    }
}
