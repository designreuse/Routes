package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Coordinate;
import com.t28.routes.entity.Entity;

public class Item extends Entity {
    @JsonProperty
    public String id;

    @JsonProperty
    public String name;

    @JsonProperty
    public Coordinate coordinate;

    @JsonProperty
    public long arriveAt;

    @JsonProperty
    public long departAt;

    @JsonProperty
    public Constraint constraint;
}
