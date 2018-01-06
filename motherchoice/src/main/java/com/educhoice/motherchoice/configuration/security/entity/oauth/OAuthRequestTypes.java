package com.educhoice.motherchoice.configuration.security.entity.oauth;

import com.educhoice.motherchoice.configuration.security.entity.UserLoginResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum OAuthRequestTypes {

    JOIN(
            (req, res, auth) -> {

            }
    ),
    LOGIN(
            (req, res, auth) -> {

            }
    );

    private AuthenticationSuccessAction action;

    OAuthRequestTypes(AuthenticationSuccessAction action) {
        this.action = action;
    }

    public void processAuthenticationPostProcess(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
        this.action.onAuthenticationSuccess(req, res, auth);
    }

    private void writeHttpBody(HttpServletResponse res, UserLoginResponseEntity entity) {
        res.setStatus(entity.getStatusCode());
        try{
            String body = new ObjectMapper().writeValueAsString(entity);
            res.getWriter().write(body);
            res.flushBuffer();
        }catch (Exception e) {
            e.getStackTrace();
        }
    }
}
