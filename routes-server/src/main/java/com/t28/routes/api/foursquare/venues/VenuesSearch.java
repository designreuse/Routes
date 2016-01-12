package com.t28.routes.api.foursquare.venues;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.t28.routes.api.foursquare.entity.Meta;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VenuesSearch {
    @JsonProperty(required = true)
    private Meta meta;

    @JsonProperty(required = true)
    private Response response;

}
