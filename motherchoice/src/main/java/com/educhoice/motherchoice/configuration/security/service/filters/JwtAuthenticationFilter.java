package com.educhoice.motherchoice.configuration.security.service.filters;

import com.educhoice.motherchoice.configuration.security.service.social.SocialLoginAuthenticationManager;
import com.educhoice.motherchoice.configuration.security.service.social.token.LoginAttemptToken;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private SocialLoginAuthenticationManager authenticationManager;
    private JwtAccessTokenConverter tokenConverter;

    protected JwtAuthenticationFilter(String defaultFilterProcessesUrl, SocialLoginAuthenticationManager authenticationManager, JwtAccessTokenConverter tokenConverter) {
        super(defaultFilterProcessesUrl);
        this.authenticationManager = authenticationManager;
        this.tokenConverter = tokenConverter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        //TODO issue OAuth2Authentication token.
        try {
            SocialAuthinfoDto dto = new ObjectMapper().readValue(req.getInputStream(), SocialAuthinfoDto.class);

            return authenticationManager.authenticate(new LoginAttemptToken(dto));

        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {

    }
}
