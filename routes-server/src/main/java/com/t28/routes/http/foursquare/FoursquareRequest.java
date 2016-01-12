package com.t28.routes.http.foursquare;

import com.t28.routes.http.Request;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
public abstract class FoursquareRequest<T> extends Request<T> {
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_VERSION = "v";
    private static final String KEY_MODE = "m";

    private final Foursquare context;
    private final Foursquare.Mode mode;

    protected FoursquareRequest(Foursquare context, Foursquare.Mode mode) {
        this.context = context;
        this.mode = mode;
    }

    public FoursquareRequest<T> prepare() {
        query(KEY_CLIENT_ID, context.getClientId());
        query(KEY_CLIENT_SECRET, context.getClientSecret());
        query(KEY_VERSION, context.getVersion());
        query(KEY_MODE, mode.name().toLowerCase());
        return this;
    }
}
