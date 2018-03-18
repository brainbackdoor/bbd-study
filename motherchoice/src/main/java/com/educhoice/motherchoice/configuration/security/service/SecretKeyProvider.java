package com.educhoice.motherchoice.configuration.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretKeyProvider {

    @Value("${security.signing-key}")
    private String signingKey;

    public byte[] getKey() {
        return signingKey.getBytes();
    }
}
