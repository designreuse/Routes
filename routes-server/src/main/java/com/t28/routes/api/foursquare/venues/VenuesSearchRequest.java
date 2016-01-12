package com.t28.routes.api.foursquare.venues;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.t28.routes.api.ApiException;
import com.t28.routes.api.Response;
import com.t28.routes.api.foursquare.Foursquare;
import com.t28.routes.api.foursquare.FoursquareRequest;

public class VenuesSearchRequest extends FoursquareRequest<VenuesSearch> {
    private static final String URL = "https://api.foursquare.com/v2/venues/search";
    private static final String KEY_QUERY = "query";
    private static final String KEY_COORDINATE = "ll";
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_LIMIT = "limit";

    protected VenuesSearchRequest(Foursquare context, Foursquare.Mode mode) {
        super(context, mode);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public Response<VenuesSearch> send() throws ApiException {
        try {
            final HttpResponse<VenuesSearch> response = get(VenuesSearch.class);
            return Response.from(response);
        } catch (UnirestException e) {
            throw new ApiException(e);
        }
    }

    public VenuesSearchRequest query(String query) {
        return (VenuesSearchRequest) query(KEY_QUERY, query);
    }

    public VenuesSearchRequest coordinate(double lat, double lon) {
        return (VenuesSearchRequest) query(KEY_COORDINATE, String.format("%f,%f", lat, lon));
    }

    public VenuesSearchRequest radius(int radius) {
        return (VenuesSearchRequest) query(KEY_RADIUS, String.valueOf(radius));
    }

    public VenuesSearchRequest limit(String limit) {
        return (VenuesSearchRequest) query(KEY_LIMIT, limit);
    }
}
