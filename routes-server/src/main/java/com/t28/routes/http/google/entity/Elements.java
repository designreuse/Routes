package com.t28.routes.http.google.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Elements {
    @JsonProperty
    private List<Element> elements;

    public Elements() {
        elements = new ArrayList<Element>();
    }

    public int size() {
        return elements.size();
    }

    public Element get(int index) {
        return elements.get(index);
    }
}
