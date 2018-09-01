package com.megapapa.impulse.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MainController {

    @GET
    public String hello() {
        return "Test";
    }
}
