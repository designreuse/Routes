package com.t28.routes.http.google;

import com.t28.routes.http.HttpRequest;

public abstract class GoogleRequest<T> extends HttpRequest<T> {
    private static final String KEY_APP_ID = "app_id";

    private final Google context;

    protected GoogleRequest(Google context) {
        this.context = context;
    }

    public GoogleRequest<T> prepare() {
        query(KEY_APP_ID, context.getAppId());
        return this;
    }
}
