package com.t28.routes.api.foursquare;

import com.t28.routes.api.foursquare.venues.VenuesClient;
import lombok.AccessLevel;
import lombok.Getter;

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
