package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

import java.util.List;
import java.util.Set;

public class Itinerary extends Entity {
    @JsonProperty("_id")
    public String id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String description;

    @JsonProperty
    public Item origin;

    @JsonProperty
    public Item destination;

    @JsonProperty
    public Set<Item> items;

    @JsonProperty
    public List<Item> route;
}
