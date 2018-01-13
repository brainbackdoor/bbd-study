package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.configuration.security.service.SocialSigninProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Arrays;

@Service
public class AuthHttpRequestService {

    private static final Logger log = LoggerFactory.getLogger(AuthHttpRequestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public SocialUserinfo retrieveSocialUserInfo(SocialSigninProviders providers, String bearerToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(providers.getUserinfoUri(), HttpMethod.GET, entity, String.class);

        log.debug(result.getBody());

        return null;
    }

}
