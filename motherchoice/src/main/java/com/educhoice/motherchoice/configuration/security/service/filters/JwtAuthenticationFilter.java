package com.educhoice.motherchoice.configuration.security.service.filters;

import com.educhoice.motherchoice.configuration.security.service.social.IntegratedLoginAuthenticationManager;
import com.educhoice.motherchoice.configuration.security.service.social.token.LoginAttemptToken;
import com.educhoice.motherchoice.configuration.security.service.social.token.SocialLoginAccessToken;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.service.JwtIdService;
import com.educhoice.motherchoice.utils.exceptions.security.InvalidAccessTokenException;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements Ordered {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private IntegratedLoginAuthenticationManager authenticationManager;
    private JwtAccessTokenConverter tokenConverter;
    private JwtIdService idService;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, IntegratedLoginAuthenticationManager authenticationManager, JwtAccessTokenConverter tokenConverter, JwtIdService idService) {
        super(defaultFilterProcessesUrl);
        this.authenticationManager = authenticationManager;
        this.tokenConverter = tokenConverter;
        this.idService = idService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        SocialAuthinfoDto dto;
        Authentication authentication;
        try {
            dto = new ObjectMapper().readValue(req.getInputStream(), SocialAuthinfoDto.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        try {
            authentication = authenticationManager.authenticate(new LoginAttemptToken(dto));
        } catch (Exception e) {
            throw new InvalidAccessTokenException("유효한 OAuth 토큰이 아닙니다.");
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {

        OAuth2Authentication authentication = auth instanceof OAuth2Authentication ? (OAuth2Authentication)auth : null;
        SecurityAccount userinfo = authentication.getPrincipal() instanceof SecurityAccount ? (SecurityAccount) authentication.getPrincipal() : null;

        SecurityContextHolder.getContext().setAuthentication(authentication);
        res.setStatus(HttpStatus.OK.value());
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(generateJwtString(new SocialLoginAccessToken(userinfo, idService.generateJti(userinfo.getUsername())), authentication));

    }

    public Authentication authenticateFromDto(SocialAuthinfoDto dto) {
        return authenticationManager.authenticate(new LoginAttemptToken(dto));
    }

    public String generateJwtString(OAuth2AccessToken token, OAuth2Authentication authentication) throws JsonProcessingException{
        OAuth2AccessToken enhancedToken = tokenConverter.enhance(token, authentication);
        return new ObjectMapper().writeValueAsString(enhancedToken);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
