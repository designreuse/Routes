package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.entity.Entity;

public class Category extends Entity {
    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String name;

    public Category() {
    }

    private Category(Builder builder) {
        super();
        id = builder.id;
        name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
