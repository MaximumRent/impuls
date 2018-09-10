package com.megapapa.impulse.controller;

import com.megapapa.impulse.entity.cayenne.IndividualTask;
import com.megapapa.impulse.entity.cayenne.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/itask")
public class IndividualTaskController {

    @GET
    @Path("{task_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public IndividualTask getITaskById(@PathParam("task_id") int id) {
        IndividualTask itask = new IndividualTask();
        itask.setDescription("asd");
//        User user = new User();
//        user.setEmail("emailllasdl");
//        itask.setUser(user);
        return itask;
    }
}
