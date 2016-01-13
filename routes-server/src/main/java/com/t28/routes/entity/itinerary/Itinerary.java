package com.t28.routes.entity.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;
import com.t28.routes.entity.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Itinerary extends Entity {
    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String description;

    @JsonProperty
    private List<Entry> entries;

    public Itinerary() {
        this.entries = new ArrayList<Entry>();
    }

    public boolean hasId() {
        return id != null;
    }

    public String getId() {
        if (id == null) {
            return null;
        }
        return id.getValue();
    }

    public boolean hasItems() {
        return entries != null && entries.size() != 0;
    }

    public List<Entry> getEntries() {
        return new ArrayList<Entry>(entries);
    }

    public void add(Entry entry) {
        this.entries.add(entry);
    }
}
