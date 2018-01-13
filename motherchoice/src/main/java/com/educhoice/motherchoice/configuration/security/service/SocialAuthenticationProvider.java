package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SocialAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private IntegratedUserQueryService userQueryService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        IntegratedUserSigninToken token = (IntegratedUserSigninToken) authentication.getPrincipal();

        if(token.isSocialRequest()) {

        }

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
