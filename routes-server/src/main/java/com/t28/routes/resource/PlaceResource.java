package com.t28.routes.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/place")
@Produces(MediaType.APPLICATION_JSON)
public class PlaceResource {

    @GET
    public Response search(@QueryParam("query") String query, @QueryParam("lat") double lat, @QueryParam("lon") double lon) {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {
        return Response.ok().build();
    }
}