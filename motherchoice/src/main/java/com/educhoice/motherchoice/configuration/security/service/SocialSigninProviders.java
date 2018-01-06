package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public enum SocialSigninProviders {

    KAKAO(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("properties");
                int socialId = (int)userinfo.get("id");
                return SocialUserinfo.builder().email((String)properties.get("kaccount_email")).socialId(socialId).username((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "kakao"),
    NAVER(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("response");
                return SocialUserinfo.builder().email((String)properties.get("email")).socialId(Integer.parseInt((String)properties.get("id"))).username((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
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
