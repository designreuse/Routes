package com.t28.route;

import com.t28.route.resource.ItineraryResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import javax.print.attribute.standard.MediaSize;

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
        environment.jersey().register(new ItineraryResource(null));
    }
}
