package com.t28.routes.api.foursquare;

public class Foursquare {
    private final String clientId;
    private final String clientSecret;

    Foursquare(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
