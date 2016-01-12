package com.t28.routes.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
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
import org.bson.types.ObjectId;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/place")
@Produces("application/json; charset=utf-8")
public class PlaceResource {
    private static final int DEFAULT_LIMIT = 50;

    private final DBCollection collection;
    private final Foursquare foursquare;

    public PlaceResource(DBCollection collection, Foursquare foursquare) {
        this.collection = collection;
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
        if (Strings.isNullOrEmpty(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Place.Builder().build()).build();
        }

        final DBObject object = collection.findOne(new ObjectId(id));
        if (object == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        final String json = JSON.serialize(object);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    private Response search(final FoursquareRequest<VenuesSearch> request) throws HttpException {
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
        final List<DBObject> objects = new ArrayList<DBObject>();
        for (TinyVenue venue : venues) {
            final Place place = convertToPlace(venue);
            places.add(place);

            final String json = place.toJson();
            objects.add((DBObject) JSON.parse(json));
        }

        // TODO: to use thread pool executor
        new Thread(new Runnable() {
            @Override
            public void run() {
                final WriteResult result = collection.insert(objects);
                System.out.println(result);
            }
        }).start();

        return Response.status(Response.Status.OK).entity(places).build();
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
