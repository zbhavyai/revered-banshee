package io.zbhavyai.rest;

import java.util.List;

import io.zbhavyai.controller.ForgeCraftController;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1")
public class ForgeCraftRest {

    private final ForgeCraftController controller;

    @Inject
    public ForgeCraftRest(ForgeCraftController fcController) {
        this.controller = fcController;
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from the server!";
    }

    @POST
    @Path("/generate-keypair")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> generateKeyPair() {
        return controller.generateKeyPair();
    }

    @POST
    @Path("/generate-certificate")
    @Produces(MediaType.TEXT_PLAIN)
    public String generateCertificate() {
        return controller.generateCertificate();
    }
}
