package com.t28.routes.api.foursquare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {
    @JsonProperty(required = true)
    private int code;

    @JsonProperty(required = true)
    private String requestId;

    @JsonProperty
    private String errorType;

    @JsonProperty
    private String errorDetail;

    public boolean hasError() {
        return !Strings.isNullOrEmpty(errorType) || !Strings.isNullOrEmpty(errorDetail);
    }
}
