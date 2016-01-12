package com.t28.routes.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public abstract class Request<T> {
    private final Map<String, Object> queries;
    private final Map<String, String> headers;
    private byte[] body;

    public Request() {
        this.queries = new HashMap<String, Object>();
        this.headers = new HashMap<String, String>();
    }

    public abstract String getUrl();

    public abstract Response<T> send() throws ApiException;

    protected HttpResponse<T> get(Class<T> responseType) throws UnirestException {
        return Unirest.get(getUrl()).queryString(queries).headers(headers).asObject(responseType);
    }

    protected Request query(String key, Object value) {
        queries.put(key, value);
        return this;
    }

    protected Request header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    protected Request body(byte[] body) {
        this.body = body.clone();
        return this;
    }
}
