package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

public class Constraint extends Entity {
    @JsonProperty
    public int stayingTime;

    @JsonProperty
    public int arriveTime;

    @JsonProperty
    public int departTime;
}
