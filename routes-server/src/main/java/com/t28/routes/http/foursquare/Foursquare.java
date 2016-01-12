package com.t28.routes.http.foursquare;

import com.t28.routes.http.foursquare.venues.VenuesClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Foursquare {
    private static final String VERSION_FORMAT = "YYYYMMDD";

    private final String clientId;
    private final String clientSecret;
    private final String version;

    Foursquare(String clientId, String clientSecret, Date date) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.version = new SimpleDateFormat(VERSION_FORMAT).format(date);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getVersion() {
        return version;
    }

    public VenuesClient venues() {
        return new VenuesClient(this);
    }

    public enum Mode {
        FOURSQUARE,
        SWARM;
    }
}
