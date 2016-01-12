package com.t28.routes.entity.place;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty(required = true)
    private final String id;

    @JsonProperty(required = true)
    private final String name;

    private Category(Builder builder) {
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
