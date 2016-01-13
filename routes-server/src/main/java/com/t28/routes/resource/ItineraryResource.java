package com.t28.routes.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.t28.routes.entity.itinerary.Constraint;
import com.t28.routes.entity.itinerary.Constraints;
import com.t28.routes.entity.itinerary.Itinerary;
import com.t28.routes.entity.place.Place;
import com.t28.routes.finder.Finder;
import com.t28.routes.finder.JspritFinder;
import com.t28.routes.finder.NotFoundException;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/itinerary")
@Produces(MediaType.APPLICATION_JSON)
public class ItineraryResource {
    private final DBCollection placeCollection;
    private final DBCollection itineraryCollection;

    public ItineraryResource(DBCollection placeCollection, DBCollection itineraryCollection) {
        this.placeCollection = placeCollection;
        this.itineraryCollection = itineraryCollection;
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Constraints constraints) {
        if (constraints == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (constraints.count() < 2) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        final Finder finder = new JspritFinder();
        for (Constraint constraint : constraints.getAll()) {
            final Place place = findPlace(constraint.getId());
            finder.add(place, constraint);
        }

        try {
            final Itinerary result = finder.find();
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update() {
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete() {
        return Response.ok().build();
    }

    private Place findPlace(String id) {
        final DBObject object = placeCollection.findOne(new ObjectId(id));
        if (object == null) {
            throw new RuntimeException("object == null");
        }

        final String json = JSON.serialize(object);
        try {
            return new ObjectMapper().readValue(json, Place.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
