package com.t28.routes.http;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse<T> {
    private final com.mashape.unirest.http.HttpResponse<T> delegate;

    private HttpResponse(com.mashape.unirest.http.HttpResponse<T> delegate) {
        this.delegate = delegate;
    }

    public int getStatusCode() {
        return delegate.getStatus();
    }

    public Map<String, List<String>> getHeaders() {
        return new HashMap<String, List<String>>(delegate.getHeaders());
    }

    public T getResult() {
        return delegate.getBody();
    }

    public String getResultAsText() throws HttpException {
        final InputStream input = delegate.getRawBody();
        try {
            return CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    public static <T> HttpResponse<T> from(com.mashape.unirest.http.HttpResponse<T> delegate) {
        return new HttpResponse<T>(delegate);
    }
}
