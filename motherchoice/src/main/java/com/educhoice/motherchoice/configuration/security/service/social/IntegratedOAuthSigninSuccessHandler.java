package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.configuration.security.entity.oauth.AuthenticationSuccessAction;
import com.educhoice.motherchoice.configuration.security.entity.oauth.OAuthRequestTypes;
import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.configuration.security.entity.UserLoginExceptionEntity;
import com.educhoice.motherchoice.configuration.security.service.AccountDetailsService;
import com.educhoice.motherchoice.configuration.security.service.SocialSigninProviders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class IntegratedOAuthSigninSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Autowired
    private AccountDetailsService accountDetailsService;

    private SocialSigninProviders providers;
    private OAuthRequestTypes types;

    public IntegratedOAuthSigninSuccessHandler(String defaultUri) {
        setDefaultTargetUrl(defaultUri);
    }

    public IntegratedOAuthSigninSuccessHandler(String defaultUri, SocialSigninProviders providers) {
        setDefaultTargetUrl(defaultUri);
        this.providers = providers;
    }

    public IntegratedOAuthSigninSuccessHandler(String defaultUri, SocialSigninProviders providers, OAuthRequestTypes types) {
        setDefaultTargetUrl(defaultUri);
        this.providers = providers;
        this.types = types;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException{

        OAuth2Authentication authentication = auth instanceof OAuth2Authentication ? (OAuth2Authentication)auth : null;

        SocialUserinfo info = this.providers.extractUserInfo((Map<String, Object>)authentication.getUserAuthentication().getDetails());

        try {
            UserDetails details = accountDetailsService.loadUserBySocialId(info.getSocialId());
            SecurityContextHolder.getContext().setAuthentication(new IntegratedUserSigninToken(details, null, details.getAuthorities()));
        } catch(Exception e) {
            SecurityContextHolder.getContext().setAuthentication(new IntegratedUserSigninToken(info));
            UserLoginExceptionEntity entity = new UserLoginExceptionEntity(e, HttpStatus.BAD_REQUEST);
            String json = new ObjectMapper().writeValueAsString(entity);
            writeHttpResponse(res, HttpStatus.BAD_REQUEST, json);
        }
    }

    private void writeHttpResponse(HttpServletResponse res, HttpStatus status, String body) throws IOException {
        res.setStatus(status.value());
        res.getWriter().write(body);
        res.flushBuffer();
    }
}
