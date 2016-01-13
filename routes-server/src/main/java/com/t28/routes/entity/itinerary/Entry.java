package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Coordinate;
import com.t28.routes.entity.Entity;
import com.t28.routes.entity.place.Place;

public class Entry extends Entity {
    @JsonProperty
    private String id;

    @JsonProperty
    private Place place;

    @JsonProperty
    private long arriveAt;

    @JsonProperty
    private long departAt;

    public Entry(String id, Place place, long arriveAt, long departAt) {
        this.id = id;
        this.place = place;
        this.arriveAt = arriveAt;
        this.departAt = departAt;
    }

    public String getId() {
        return id;
    }
}
