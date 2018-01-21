package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AuthHttpRequestService {

    private static final Logger log = LoggerFactory.getLogger(AuthHttpRequestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public BasicSocialUserInfo retrieveSocialUserInfo(SocialSigninProviders providers, SocialAuthinfoDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.set("Authorization", generateHeaderValue(dto));


        ResponseEntity<? extends BasicSocialUserInfo> result = restTemplate.exchange(providers.getUserinfoUri(), HttpMethod.GET, new HttpEntity<String>(headers), providers.getUserinfoDtoClass());

        log.debug("fetched user info from provider {} : {}", providers.getProviderName(), result.getBody().toString());

        return result.getBody();
    }

    private String generateHeaderValue(SocialAuthinfoDto dto) {
        StringBuilder builder = new StringBuilder();

        builder.append("Bearer ");
        builder.append(dto.getAccessToken());

        return builder.toString();
    }

}
