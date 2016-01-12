package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Places extends Entity {
    @JsonProperty(required = true)
    private final List<Place> places;

    public Places(List<Place> places) {
        super();
        this.places = new ArrayList<Place>(places);
    }
}
