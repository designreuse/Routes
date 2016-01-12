package com.t28.route;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class RouteApplication extends Application<RouteConfiguration> {
    public static void main(String[] args) throws Exception {
        new RouteApplication().run(args);
    }

    @Override
    public void run(RouteConfiguration configuration, Environment environment) throws Exception {

    }
}
