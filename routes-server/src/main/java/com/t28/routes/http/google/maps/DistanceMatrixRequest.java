package com.t28.routes.http.google.maps;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.t28.routes.http.HttpException;
import com.t28.routes.http.HttpResponse;
import com.t28.routes.http.google.Google;
import com.t28.routes.http.google.GoogleRequest;
import com.t28.routes.http.google.entity.DistanceMatrix;

public class DistanceMatrixRequest extends GoogleRequest<DistanceMatrix> {
    private static final String URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static final String KEY_ORIGINS = "origins";
    private static final String KEY_DESTINATIONS = "destinations";
    private static final String KEY_TRANSIT_MODE = "transit_mode";
    private static final String KEY_LANGUAGE = "language";

    protected DistanceMatrixRequest(Google context) {
        super(context);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public HttpResponse<DistanceMatrix> send() throws HttpException {
        try {
            return HttpResponse.from(get(DistanceMatrix.class));
        } catch (UnirestException e) {
            throw new HttpException(e);
        }
    }

    public DistanceMatrixRequest origin(double lat, double lon) {
        return (DistanceMatrixRequest) query(KEY_ORIGINS, String.join(",", String.valueOf(lat), String.valueOf(lon)));
    }

    public DistanceMatrixRequest destination(double lat, double lon) {
        return (DistanceMatrixRequest) query(KEY_DESTINATIONS, String.join(",", String.valueOf(lat), String.valueOf(lon)));
    }

    public DistanceMatrixRequest origins(String... origins) {
        return (DistanceMatrixRequest) query(KEY_ORIGINS, String.join("|", origins));
    }

    public DistanceMatrixRequest destinations(String... destinations) {
        return (DistanceMatrixRequest) query(KEY_DESTINATIONS, String.join("|", destinations));
    }

    public DistanceMatrixRequest transitMode(String... modes) {
        return (DistanceMatrixRequest) query(KEY_TRANSIT_MODE, String.join("|", modes));
    }

    public DistanceMatrixRequest language(String language) {
        return (DistanceMatrixRequest) query(KEY_LANGUAGE, language);
    }
}
