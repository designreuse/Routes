package com.t28.routes.mongodb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.net.UnknownHostException;

public class MongodbFactory extends Configuration {
    private static final String DB_NAME = "routes";
    @Valid
    @NotEmpty
    @JsonProperty
    private String host;

    @Valid
    @JsonProperty
    private int port;

    public DB create() throws MongodbException {
        try {
            final MongoClient client = new MongoClient(host, port);
            return client.getDB(DB_NAME);
        } catch (UnknownHostException e) {
            throw new MongodbException(e);
        }
    }
}
