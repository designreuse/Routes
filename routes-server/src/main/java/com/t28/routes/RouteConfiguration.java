package com.t28.routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.mongodb.MongodbFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RouteConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("mongodb")
    private MongodbFactory mongodbFactory;

    // TODO: Need to fix a bug
    public MongodbFactory getMongodbFactory() {
        return mongodbFactory;
    }
}
