package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;
import com.t28.routes.entity.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Place extends Entity {
    @JsonProperty("_id")
    private ObjectId objectId;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String foursquareId;

    @JsonProperty(required = true)
    private Location location;

    @JsonProperty(required = true)
    private List<Category> categories;

    public Place() {
    }

    private Place(Builder builder) {
        super();
        name = builder.name;
        foursquareId = builder.foursquareId;
        location = builder.location;
        categories = new ArrayList<Category>(builder.categories);
    }

    public String getObjectId() {
        if (objectId == null) {
            return null;
        }
        return objectId.getValue();
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
