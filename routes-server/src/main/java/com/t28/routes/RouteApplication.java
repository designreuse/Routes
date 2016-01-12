package com.t28.routes;

import com.mongodb.DB;
import com.t28.routes.mongodb.MongodbFactory;
import com.t28.routes.resource.ItineraryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class RouteApplication extends Application<RouteConfiguration> {
    private static final String NAME = "Routes";

    public static void main(String[] args) throws Exception {
        new RouteApplication().run(args);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void run(RouteConfiguration configuration, Environment environment) throws Exception {
        final MongodbFactory factory = configuration.getMongodbFactory();
        final DB database = factory.create();
        environment.jersey().register(new ItineraryResource(database.getCollection("itinerary")));
    }
}
