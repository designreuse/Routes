package com.t28.routes.http.foursquare.venues;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.t28.routes.http.HttpException;
import com.t28.routes.http.HttpResponse;
import com.t28.routes.http.foursquare.Foursquare;
import com.t28.routes.http.foursquare.FoursquareRequest;

public class VenuesSearchRequest extends FoursquareRequest<VenuesSearch> {
    private static final String URL = "https://api.foursquare.com/v2/venues/search";
    private static final String KEY_QUERY = "query";
    private static final String KEY_CATEGORY_ID = "categoryId";
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
    public HttpResponse<VenuesSearch> send() throws HttpException {
        try {
            return HttpResponse.from(get(VenuesSearch.class));
        } catch (UnirestException e) {
            throw new HttpException(e);
        }
    }

    public VenuesSearchRequest query(String query) {
        return (VenuesSearchRequest) query(KEY_QUERY, query);
    }

    public VenuesSearchRequest category(String category) {
        return (VenuesSearchRequest) query(KEY_CATEGORY_ID, category);
    }

    public VenuesSearchRequest coordinate(double lat, double lon) {
        return (VenuesSearchRequest) query(KEY_COORDINATE, String.format("%f,%f", lat, lon));
    }

    public VenuesSearchRequest radius(int radius) {
        return (VenuesSearchRequest) query(KEY_RADIUS, String.valueOf(radius));
    }

    public VenuesSearchRequest limit(int limit) {
        return (VenuesSearchRequest) query(KEY_LIMIT, limit);
    }
}
