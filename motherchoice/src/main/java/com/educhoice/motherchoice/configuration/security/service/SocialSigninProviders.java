package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.SocialUserinfo;

import java.util.Map;

public enum SocialSigninProviders {

    KAKAO(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("properties");
                return SocialUserinfo.builder().username((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "kakao"),
    NAVER(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("response");
                return SocialUserinfo.builder().username((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "naver");

    private SocialUserinfoExtractor extractor;
    private String symbol;

    SocialSigninProviders(SocialUserinfoExtractor extractor, String symbol){
        this.extractor = extractor;
        this.symbol = symbol;
    }

    public SocialUserinfo extractUserInfo(Map<String, Object> userinfo) {
        return this.extractor.retrieveUserinfo(userinfo);
    }
}
