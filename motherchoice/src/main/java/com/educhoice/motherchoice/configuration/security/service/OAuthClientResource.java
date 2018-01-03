package com.educhoice.motherchoice.configuration.security.service;

import lombok.Getter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Getter
public class OAuthClientResource {

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails clientDetails = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resourceDetails = new ResourceServerProperties();

}
