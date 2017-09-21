package com.home.controller;

import com.home.model.Person;
import com.home.service.DataService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by amarendra on 20/09/17.
 */
@Path("/msg")
public class PersonController {

    @Inject
    private DataService dataService;

    @GET
    @Path("/byID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@QueryParam("id") Integer id) {

        if (id == null) {
            Response.status(Response.Status.BAD_REQUEST).build();
        }

        Person person = this.dataService.getByID(id);
        System.out.println(person);
        if (person == null) {
            Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(person).build();
    }

    @GET
    @Path("add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response add() {
        this.dataService.add();
        return Response.status(Response.Status.OK).entity("Added !!").build();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.status(Response.Status.OK).entity("Test OK!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(this.dataService.getData().values()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response set(Person person) {
        Integer id = this.dataService.save(person);
        Person byID = this.dataService.getByID(id);
        return Response.status(Response.Status.OK).entity(byID).build();
    }
}

