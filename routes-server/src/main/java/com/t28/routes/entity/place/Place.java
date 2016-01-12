package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Place extends Entity {
    @JsonProperty("_id")
    private final String id;

    @JsonProperty(required = true)
    private final String name;

    @JsonProperty(required = true)
    private final String foursquareId;

    @JsonProperty(required = true)
    private final Location location;

    @JsonProperty(required = true)
    private final List<Category> categories;

    private Place(Builder builder) {
        super();
        id = builder.id;
        name = builder.name;
        foursquareId = builder.foursquareId;
        location = builder.location;
        categories = new ArrayList<Category>(builder.categories);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public Location getLocation() {
        return location;
    }

    public List<Category> getCategories() {
        return new ArrayList<Category>(categories);
    }

    public static class Builder {
        private String id;
        private String name;
        private String foursquareId;
        private Location location;
        private final List<Category> categories;

        public Builder() {
            categories = new ArrayList<Category>();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder foursquareId(String foursquareId) {
            this.foursquareId = foursquareId;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Builder categories(List<Category> categories) {
            this.categories.addAll(categories);
            return this;
        }

        public Place build() {
            return new Place(this);
        }
    }
}
