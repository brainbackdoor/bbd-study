package com.educhoice.motherchoice.configuration.security.service.filters;

import com.educhoice.motherchoice.configuration.security.service.social.IntegratedLoginAuthenticationManager;
import com.educhoice.motherchoice.configuration.security.service.social.token.LoginAttemptToken;
import com.educhoice.motherchoice.configuration.security.service.social.token.SocialLoginAccessToken;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.service.JwtIdService;
import com.educhoice.motherchoice.utils.exceptions.security.SecurityException;
import com.educhoice.motherchoice.valueobject.models.accounts.FormLoginRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginJwtAutheticationFilter extends AbstractAuthenticationProcessingFilter {

    private IntegratedLoginAuthenticationManager authenticationManager;
    private JwtAccessTokenConverter tokenConverter;
    private JwtIdService idService;

    public FormLoginJwtAutheticationFilter(String defaultFilterProcessesUrl, IntegratedLoginAuthenticationManager authenticationManager, JwtAccessTokenConverter tokenConverter, JwtIdService idService) {
        super(defaultFilterProcessesUrl);
        this.authenticationManager = authenticationManager;
        this.tokenConverter = tokenConverter;
        this.idService = idService;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        FormLoginRequestDto dto = null;

        try {
            dto = new ObjectMapper().readValue(httpServletRequest.getInputStream(), FormLoginRequestDto.class);
        } catch (Exception e) {
            throw new SecurityException(e.getMessage());
        }

        return authenticationManager.authenticate(new LoginAttemptToken(dto));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        OAuth2Authentication authentication = (OAuth2Authentication)auth;

        SecurityAccount user = (SecurityAccount) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        res.setStatus(HttpStatus.OK.value());
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            res.getWriter().write(generateJwtString(new SocialLoginAccessToken(user, idService.generateJti(user.getUsername())), authentication));
        } catch (IOException e) {
            throw new SecurityException(e.getMessage());
        }
    }

    public String generateJwtString(OAuth2AccessToken token, OAuth2Authentication authentication) throws JsonProcessingException {
        OAuth2AccessToken enhancedToken = tokenConverter.enhance(token, authentication);
        return new ObjectMapper().writeValueAsString(enhancedToken);
    }

}
