package com.t28.routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.http.ApiConfiguration;
import com.t28.routes.mongodb.MongodbFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

// TODO: Need to fix a bug that lombok.Getter does not work appropriately
public class RouteConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("mongodb")
    private MongodbFactory mongodbFactory;

    @Valid
    @NotNull
    @JsonProperty("http")
    private ApiConfiguration apiConfiguration;

    public MongodbFactory getMongodbFactory() {
        return mongodbFactory;
    }

    public ApiConfiguration getApiConfiguration() {
        return apiConfiguration;
    }
}
