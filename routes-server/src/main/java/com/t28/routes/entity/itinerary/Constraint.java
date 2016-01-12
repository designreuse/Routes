package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

public class Constraint extends Entity {
    public static final Constraint EMPTY = new Constraint();

    @JsonProperty
    public int stayingTime;

    @JsonProperty
    public int arriveTime;

    @JsonProperty
    public int departTime;

    public boolean isEmpty() {
        return stayingTime == 0 && arriveTime == 0 && departTime == 0;
    }
}
