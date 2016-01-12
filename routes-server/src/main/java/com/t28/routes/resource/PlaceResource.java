package com.t28.routes.resource;

import com.google.common.base.Strings;
import com.mongodb.DBCollection;
import com.t28.routes.entity.place.Category;
import com.t28.routes.entity.place.Coordinate;
import com.t28.routes.entity.place.Location;
import com.t28.routes.entity.place.Place;
import com.t28.routes.http.HttpException;
import com.t28.routes.http.HttpResponse;
import com.t28.routes.http.foursquare.Foursquare;
import com.t28.routes.http.foursquare.FoursquareRequest;
import com.t28.routes.http.foursquare.entity.Meta;
import com.t28.routes.http.foursquare.entity.TinyVenue;
import com.t28.routes.http.foursquare.venues.VenuesSearch;
import com.t28.routes.http.foursquare.venues.VenuesSearchRequest;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/place")
@Produces("application/json; charset=utf-8")
public class PlaceResource {
    private static final int DEFAULT_LIMIT = 50;

    private final JacksonDBCollection<Place, String> collection;
    private final Foursquare foursquare;

    public PlaceResource(DBCollection collection, Foursquare foursquare) {
        this.collection = JacksonDBCollection.wrap(collection, Place.class, String.class);
        this.foursquare = foursquare;
    }

    @GET
    public Response search(@QueryParam("lat") double lat, @QueryParam("lon") double lon,
                           @QueryParam("query") String query, @QueryParam("category") String category) {
        try {
            final VenuesSearchRequest request = foursquare.venues().search();
            request.coordinate(lat, lon);
            request.limit(DEFAULT_LIMIT);
            if (!Strings.isNullOrEmpty(query)) {
                request.query(query);
            }
            if (!Strings.isNullOrEmpty(category)) {
                request.category(category);
            }
            return search(request);
        } catch (HttpException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {
        return Response.ok().build();
    }

    private Response search(FoursquareRequest<VenuesSearch> request) throws HttpException {
        HttpResponse<VenuesSearch> httpResponse = request.send();
        if (httpResponse.getStatusCode() != Response.Status.OK.getStatusCode()) {
            throw new HttpException("Received status code is not expected:" + httpResponse.getStatusCode());
        }

        final VenuesSearch body = httpResponse.getResult();
        final Meta meta = body.getMeta();
        if (meta.getCode() != Meta.CODE_OK) {
            throw new HttpException("Received meta is not successful:" + meta);
        }

        final VenuesSearch.Response response = body.getResponse();
        if (!response.hasVenues()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        final List<TinyVenue> venues = response.getVenues();
        final List<Place> places = new ArrayList<Place>();
        for (TinyVenue venue : venues) {
            final Place place = convertToPlace(venue);
            places.add(place);
        }
        // TODO:
        new Thread(new Runnable() {
            @Override
            public void run() {
                final WriteResult<Place, String> result = collection.insert(places);
            }
        }).start();
        return Response.status(Response.Status.OK).encoding("ISO_8859_1").entity(places).build();
    }

    private Place convertToPlace(TinyVenue venue) {
        return new Place.Builder()
                .name(venue.getName())
                .foursquareId(venue.getId())
                .location(convertToLocation(venue))
                .categories(convertToCategories(venue))
                .build();
    }

    private Location convertToLocation(TinyVenue venue) {
        final com.t28.routes.http.foursquare.entity.Location location = venue.getLocation();
        return new Location.Builder()
                .country(location.getCountry())
                .countryCode(location.getCountryCode())
                .state(location.getState())
                .city(location.getCity())
                .address(location.formatAddress())
                .coordinate(new Coordinate.Builder()
                        .lat(location.getLat())
                        .lon(location.getLon())
                        .build()
                )
                .build();
    }

    private List<Category> convertToCategories(TinyVenue venue) {
        final List<Category> categories = new ArrayList<Category>();
        final List<com.t28.routes.http.foursquare.entity.Category> srcCategories = venue.getCategories();
        for (com.t28.routes.http.foursquare.entity.Category category : srcCategories) {
            categories.add(new Category.Builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build());
        }
        return categories;
    }
}
