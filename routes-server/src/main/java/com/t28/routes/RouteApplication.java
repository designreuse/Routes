package com.t28.routes;

import com.mashape.unirest.http.Unirest;
import com.mongodb.DB;
import com.t28.routes.http.Headers;
import com.t28.routes.http.foursquare.Foursquare;
import com.t28.routes.http.foursquare.FoursquareFactory;
import com.t28.routes.http.unirest.JacksonMapper;
import com.t28.routes.mongodb.MongodbFactory;
import com.t28.routes.resource.ItineraryResource;
import com.t28.routes.resource.PlaceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.TimeUnit;

public class RouteApplication extends Application<RouteConfiguration> {
    private static final String NAME = "Routes";
    private static final String USER_AGENT = "User-Agent";
    private static final String ACCEPT_ENCODING = "gzip,deflate,sdch";
    private static final String ACCEPT_LANGUAGE = "ja-jp";
    private static final long DEFAULT_TIMEOUT = TimeUnit.SECONDS.toMillis(3);

    public static void main(String[] args) throws Exception {
        new RouteApplication().run(args);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void initialize(Bootstrap<RouteConfiguration> bootstrap) {
        super.initialize(bootstrap);
        Unirest.setDefaultHeader(Headers.USER_AGENT, USER_AGENT);
        Unirest.setDefaultHeader(Headers.ACCEPT_ENCODING, ACCEPT_ENCODING);
        Unirest.setDefaultHeader(Headers.ACCEPT_LANGUAGE, ACCEPT_LANGUAGE);
        Unirest.setTimeouts(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
        Unirest.setObjectMapper(new JacksonMapper());
    }

    @Override
    public void run(RouteConfiguration configuration, Environment environment) throws Exception {
        final MongodbFactory factory = configuration.getMongodbFactory();
        final DB database = factory.create();

        final FoursquareFactory foursquareFactory = configuration.getApiConfiguration().getFoursquareFactory();
        final Foursquare foursquare = foursquareFactory.create();

        environment.jersey().register(new PlaceResource(database.getCollection("place"), foursquare));
        environment.jersey().register(new ItineraryResource(database.getCollection("itinerary")));
    }
}
