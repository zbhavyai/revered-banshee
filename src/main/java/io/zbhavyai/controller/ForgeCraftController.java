package io.zbhavyai.controller;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.UUID;

import io.zbhavyai.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zbhavyai.model.KeyPairWithID;
import io.zbhavyai.service.ForgeCraft;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ForgeCraftController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgeCraftController.class.getSimpleName());

    private final ForgeCraft service;

    @Inject
    public ForgeCraftController(final ForgeCraft fcService) {
        this.service = fcService;
    }

    public KeyPairDTO generateKeyPair() {
        LOGGER.info("generateKeyPair");

        KeyPairWithID keyPair = this.service.generateKeyPair(generateUUID().toString());
        List<String> keyPairSer = this.service.serializeKeyPairToPEM(keyPair);

        return new KeyPairDTO(
                keyPairSer.get(0),
                keyPairSer.get(1),
                keyPairSer.get(2));
    }

    public CertificateDTO generateCertificate(CertificateInputDTO certInput) {
        LOGGER.info("generateCertificate");

        KeyPairWithID keyPair = this.service.generateKeyPair(generateUUID().toString());
        X509Certificate cert = this.service.generateX509Certificate(keyPair, certInput.issuer(), certInput.subject());

        return new CertificateDTO(
                this.service.serializeX509CertificateToPEM(cert),
                cert.toString());
    }

    public KeyPairAndCertificateDTO generateKeyPairAndCertificate(CertificateInputDTO certInput) {
        LOGGER.info("generateKeyPairAndCertificate");

        KeyPairWithID keyPair = this.service.generateKeyPair(generateUUID().toString());
        List<String> keyPairSer = this.service.serializeKeyPairToPEM(keyPair);

        X509Certificate cert = this.service.generateX509Certificate(keyPair, certInput.issuer(), certInput.subject());

        return new KeyPairAndCertificateDTO(
                keyPairSer.get(0),
                keyPairSer.get(1),
                keyPairSer.get(2),
                this.service.serializeX509CertificateToPEM(cert),
                cert.toString());
    }

    public KeyPairAndCertificateDTO convertKeyPairBase64ToPem(KeyPairAndCertificateInputDTO keyAndCertInput) {
        LOGGER.info("convertKeyPairBase64ToPem");

        KeyPairWithID keyPair = this.service.deserializeKeyPair(
                keyAndCertInput.keyID(),
                keyAndCertInput.publicKey(),
                keyAndCertInput.privateKey());

        List<String> keyPairSer = service.serializeKeyPairToPEM(keyPair);

        X509Certificate cert = this.service.generateX509Certificate(keyPair, keyAndCertInput.issuer(), keyAndCertInput.subject());

        return new KeyPairAndCertificateDTO(
                keyPairSer.get(0),
                keyPairSer.get(1),
                keyPairSer.get(2),
                this.service.serializeX509CertificateToPEM(cert),
                cert.toString());
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
