package com.t28.routes.http.foursquare.venues;

import com.t28.routes.http.foursquare.Foursquare;

public class VenuesClient {
    private final Foursquare context;

    public VenuesClient(Foursquare context) {
        this.context = context;
    }

    public VenuesSearchRequest search() {
        return (VenuesSearchRequest) new VenuesSearchRequest(context, Foursquare.Mode.FOURSQUARE).prepare();
    }
}
