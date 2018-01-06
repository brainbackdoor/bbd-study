package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;

import java.util.Map;

@FunctionalInterface
public interface SocialUserinfoExtractor {

    SocialUserinfo retrieveUserinfo(Map<String, Object> userinfo);
}
