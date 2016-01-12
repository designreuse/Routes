package com.t28.routes.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.http.foursquare.FoursquareFactory;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString
@EqualsAndHashCode
public class ApiConfiguration {
    @Valid
    @NotNull
    @JsonProperty("foursquare")
    private FoursquareFactory foursquareFactory;

    public FoursquareFactory getFoursquareFactory() {
        return foursquareFactory;
    }
}
