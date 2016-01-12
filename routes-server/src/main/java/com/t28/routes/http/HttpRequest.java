package com.t28.routes.http;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpRequest<T> {
    private final Map<String, Object> queries;
    private final Map<String, String> headers;
    private byte[] body;

    public HttpRequest() {
        this.queries = new HashMap<String, Object>();
        this.headers = new HashMap<String, String>();
    }

    public abstract String getUrl();

    public abstract HttpResponse<T> send() throws HttpException;

    protected com.mashape.unirest.http.HttpResponse get(Class<T> responseType) throws UnirestException {
        return Unirest.get(getUrl()).queryString(queries).headers(headers).asObject(responseType);
    }

    protected HttpRequest query(String key, Object value) {
        queries.put(key, value);
        return this;
    }

    protected HttpRequest header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    protected HttpRequest body(byte[] body) {
        this.body = body.clone();
        return this;
    }
}
