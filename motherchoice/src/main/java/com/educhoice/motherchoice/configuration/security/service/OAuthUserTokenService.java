package com.educhoice.motherchoice.configuration.security.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

public class OAuthUserTokenService extends UserInfoTokenServices {

    public OAuthUserTokenService(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
    }

    public OAuthUserTokenService(OAuthClientResource resource) {
        super(resource.getResourceDetails().getUserInfoUri(), resource.getClientDetails().getClientId());
    }

}
