package com.t28.routes.http.google;

import com.t28.routes.http.google.maps.MapsClient;

public class Google {
    private final String appId;

    Google(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public MapsClient maps() {
        return new MapsClient(this);
    }
}
