package com.t28.routes.api.foursquare;

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
        this.version = new SimpleDateFormat("YYYYMMDD").format(date);
    }

    public enum Mode {
        FOURSQUARE,
        SWARM;
    }
}
