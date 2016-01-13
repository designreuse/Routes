package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Constraints {
    @JsonProperty
    private List<Constraint> constraints;

    public int count() {
        return constraints == null ? 0 : constraints.size();
    }

    public Constraint get(int index) {
        return constraints.get(index);
    }

    public List<Constraint> getAll() {
        return new ArrayList<Constraint>(constraints);
    }
}
