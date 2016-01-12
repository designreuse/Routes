package com.t28.routes.http.foursquare.venues;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.http.foursquare.entity.Meta;
import com.t28.routes.http.foursquare.entity.TinyVenue;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VenuesSearch {
    @JsonProperty(required = true)
    private Meta meta;

    @JsonProperty(required = true)
    private Response response;

    public static class Response {
        @JsonProperty
        private List<TinyVenue> venues;

        @JsonProperty(value = "confident")
        private boolean isConfident;

        public boolean hasVenues() {
            return venues != null;
        }
    }
}
