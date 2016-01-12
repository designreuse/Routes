package com.t28.routes.api;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.mashape.unirest.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response<T> {
    private final HttpResponse<T> delegate;

    private Response(HttpResponse<T> delegate) {
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

    public String getRawResult() throws ApiException {
        final InputStream input = delegate.getRawBody();
        try {
            return CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    public static <T> Response<T> from(HttpResponse<T> delegate) {
        return new Response<T>(delegate);
    }
}
