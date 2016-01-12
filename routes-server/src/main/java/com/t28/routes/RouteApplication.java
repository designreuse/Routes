package com.t28.routes;

import com.mashape.unirest.http.Unirest;
import com.mongodb.DB;
import com.t28.routes.api.Response;
import com.t28.routes.api.foursquare.Foursquare;
import com.t28.routes.api.foursquare.FoursquareFactory;
import com.t28.routes.api.foursquare.venues.VenuesSearchResponse;
import com.t28.routes.api.unirest.JacksonMapper;
import com.t28.routes.mongodb.MongodbFactory;
import com.t28.routes.resource.ItineraryResource;
import com.t28.routes.resource.PlaceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.TimeUnit;

public class RouteApplication extends Application<RouteConfiguration> {
    private static final String NAME = "Routes";
    private static final String USER_AGENT_HEADER_KEY = "User-Agent";
    private static final String USER_AGENT_HEADER_VALUE = "User-Agent";
    private static final String ACCEPT_ENCODING_HEADER_KEY = "Accept-Encoding";
    private static final String ACCEPT_ENCODING_HEADER_VALUE = "gzip,deflate,sdch";
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
        Unirest.setDefaultHeader(USER_AGENT_HEADER_KEY, USER_AGENT_HEADER_VALUE);
        Unirest.setDefaultHeader(ACCEPT_ENCODING_HEADER_KEY, ACCEPT_ENCODING_HEADER_VALUE);
        Unirest.setTimeouts(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
        Unirest.setObjectMapper(new JacksonMapper());
    }

    @Override
    public void run(RouteConfiguration configuration, Environment environment) throws Exception {
        final MongodbFactory factory = configuration.getMongodbFactory();
        final DB database = factory.create();

        final FoursquareFactory foursquareFactory = configuration.getApiConfiguration().getFoursquareFactory();
        final Foursquare foursquare = foursquareFactory.create();
        final Object object = foursquare.venues().search();
        final Response<VenuesSearchResponse> response = foursquare.venues().search().coordinate(40.7, -74).send();
        environment.jersey().register(new PlaceResource());
        environment.jersey().register(new ItineraryResource(database.getCollection("itinerary")));
    }
}
