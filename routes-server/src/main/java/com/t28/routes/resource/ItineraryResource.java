package com.t28.routes.resource;

import com.mongodb.DBCollection;

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

@Path("/itinerary")
@Produces(MediaType.APPLICATION_JSON)
public class ItineraryResource {
    private final DBCollection collection;

    public ItineraryResource(DBCollection collection) {
        this.collection = collection;
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create() {
        return Response.ok().build();
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
}
