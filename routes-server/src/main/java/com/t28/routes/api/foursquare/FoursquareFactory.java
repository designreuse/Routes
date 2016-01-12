package com.t28.routes.api.foursquare;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.Date;

public class FoursquareFactory {
    @Valid
    @NotEmpty
    @JsonProperty("client_id")
    private String clientId;

    @Valid
    @NotEmpty
    @JsonProperty("client_secret")
    private String clientSecret;

    public Foursquare create() {
        return new Foursquare(clientId, clientSecret, new Date());
    }
}
