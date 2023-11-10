package io.zbhavyai.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zbhavyai.model.KeyPairWithID;
import io.zbhavyai.service.ForgeCraft;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ForgeCraftController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgeCraftController.class);

    private final ForgeCraft service;

    @Inject
    public ForgeCraftController(final ForgeCraft fcService) {
        this.service = fcService;
    }

    public List<String> generateKeyPair() {
        LOGGER.info("generateKeyPair");

        KeyPairWithID keyPair = this.service.generateKeyPair(generateUUID().toString());
        return this.service.serializeKeyPairToPEM(keyPair);
    }

    public String generateCertificate() {
        LOGGER.info("generateCertificate");

        KeyPairWithID keyPair = this.service.generateKeyPair(generateUUID().toString());
        return this.service
                .serializeX509CertificateToPEM(this.service.generateX509Certificate(keyPair, "issuer", "subject"));
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
