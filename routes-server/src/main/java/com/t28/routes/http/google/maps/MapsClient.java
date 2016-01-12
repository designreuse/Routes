package com.t28.routes.http.google.maps;

import com.t28.routes.http.google.Google;

public class MapsClient {
    private final Google context;

    public MapsClient(Google context) {
        this.context = context;
    }

    public DistanceMatrixRequest distanceMatrix() {
        return new DistanceMatrixRequest(context);
    }
}
