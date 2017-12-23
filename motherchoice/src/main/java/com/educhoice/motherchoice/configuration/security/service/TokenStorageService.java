package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class TokenStorageService {

    private final int EXPIRY_HOURS = 1;
    private static final Logger log = LoggerFactory.getLogger(TokenStorageService.class);

    public LoadingCache<String, Token> cache;

    public TokenStorageService() {
        super();
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite((long)EXPIRY_HOURS, TimeUnit.HOURS)
                .build(new CacheLoader<String, Token>() {
                    @Override
                    public Token load(String s) throws Exception {
                        return new Token();
                    }
                });
    }

    public void confirm(String email) {
        try {
            if(this.cache.get(email).isMatchingEmail(email)) {
                this.cache.invalidate(this.cache.get(email));
            }
        } catch(ExecutionException e) {
            log.error(e.getMessage());
        }

    }

}
