package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.SocialUserinfo;

import java.util.Map;

@FunctionalInterface
public interface SocialUserinfoExtractor {

    public SocialUserinfo retrieveUserinfo(Map<String, Object> userinfo);
}
