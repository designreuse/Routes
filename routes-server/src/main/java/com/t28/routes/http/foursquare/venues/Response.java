package com.t28.routes.http.foursquare.venues;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.http.foursquare.entity.TinyVenue;

import java.util.List;

public class Response {
    @JsonProperty
    private List<TinyVenue> venues;

    @JsonProperty(value = "confident")
    private boolean isConfident;

    public boolean hasVenues() {
        return venues != null;
    }
}
