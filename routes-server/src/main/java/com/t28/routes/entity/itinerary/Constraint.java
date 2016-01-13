package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.t28.routes.entity.Entity;

public class Constraint extends Entity {
    public static final Constraint EMPTY = new Constraint();

    @JsonProperty
    private String id;

    @JsonProperty
    private int stayingTime;

    @JsonProperty
    private int arriveTime;

    @JsonProperty
    private int departTime;

    public boolean isEmpty() {
        if (!Strings.isNullOrEmpty(id)) {
            return false;
        }
        return stayingTime == 0 && arriveTime == 0 && departTime == 0;
    }

    public String getId() {
        return id;
    }

    public int getStayingTime() {
        return stayingTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getDepartTime() {
        return departTime;
    }
}
