package com.t28.routes.http.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

public class GoogleFactory {
    @Valid
    @NotEmpty
    @JsonProperty("app_id")
    private String appId;

    public Google create() {
        return new Google(appId);
    }
}
