package com.t28.routes.http.foursquare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

public class Meta {
    public static final int CODE_OK = 200;

    @JsonProperty(required = true)
    private int code;

    @JsonProperty(required = true)
    private String requestId;

    @JsonProperty
    private String errorType;

    @JsonProperty
    private String errorDetail;

    public int getCode() {
        return code;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public boolean hasError() {
        return !Strings.isNullOrEmpty(errorType) || !Strings.isNullOrEmpty(errorDetail);
    }
}
