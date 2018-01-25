package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthHttpRequestService {

    private static final Logger log = LoggerFactory.getLogger(AuthHttpRequestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public BasicSocialUserInfo retrieveSocialUserInfo(SocialAuthinfoDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", generateHeaderValue(dto));


        log.debug(headers.toString());
        ResponseEntity<? extends BasicSocialUserInfo> result = restTemplate.exchange(dto.getUserinfoRestResource(), HttpMethod.GET, new HttpEntity<String>(headers), dto.getUserinfoMetaclass());

        log.debug("fetched user info from provider {} : {}", dto.getProvider().getProviderName(), result.getBody().toString());

        return result.getBody();
    }

    private String generateHeaderValue(SocialAuthinfoDto dto) {
        StringBuilder builder = new StringBuilder();

        builder.append("Bearer ");
        builder.append(dto.getAccessToken());

        log.debug("generated HTTP request header authorization is : {}" , builder.toString());
        return builder.toString();
    }

}
