package io.zbhavyai.rest;

import io.zbhavyai.controller.ForgeCraftController;
import io.zbhavyai.dto.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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
    public KeyPairDTO generateKeyPair() {
        return controller.generateKeyPair();
    }

    @POST
    @Path("/generate-certificate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CertificateDTO generateCertificate(CertificateInputDTO certInput) {
        return controller.generateCertificate(certInput);
    }

    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public KeyPairAndCertificateDTO generateKeyAndCertificate(CertificateInputDTO certInput) {
        return controller.generateKeyPairAndCertificate(certInput);
    }

    @POST
    @Path("/keypair-base64-pem")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public KeyPairAndCertificateDTO convertKeyPairBase64ToPem(KeyPairAndCertificateInputDTO keyAndCertInput) {
        return controller.convertKeyPairBase64ToPem(keyAndCertInput);
    }
}
