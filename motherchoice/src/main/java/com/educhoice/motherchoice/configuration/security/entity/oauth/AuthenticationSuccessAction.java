package com.educhoice.motherchoice.configuration.security.entity.oauth;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface AuthenticationSuccessAction {

    void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth);
}
