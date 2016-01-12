package com.t28.routes.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.http.foursquare.FoursquareFactory;
import com.t28.routes.http.google.GoogleFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiConfiguration {
    @Valid
    @NotNull
    @JsonProperty("google")
    private GoogleFactory googleFactory;

    @Valid
    @NotNull
    @JsonProperty("foursquare")
    private FoursquareFactory foursquareFactory;

    public GoogleFactory getGoogleFactory() {
        return googleFactory;
    }

    public FoursquareFactory getFoursquareFactory() {
        return foursquareFactory;
    }
}
