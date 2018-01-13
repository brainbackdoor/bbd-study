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
                return SocialUserinfo.builder().loginId((String)properties.get("kaccount_email")).socialId(socialId).nickname((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "kakao", "https://kapi.kakao.com/v1/user/me"),
    NAVER(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("response");
                return SocialUserinfo.builder().loginId((String)properties.get("loginId")).socialId(Integer.parseInt((String)properties.get("id"))).nickname((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "naver", "https://openapi.naver.com/v1/nid/me"),

    TEST(
            null, "test", "http://www.naver.com"
    );

    private SocialUserinfoExtractor extractor;
    private String symbol;
    private String userinfoUri;

    SocialSigninProviders(SocialUserinfoExtractor extractor, String symbol, String userinfoUri){
        this.extractor = extractor;
        this.symbol = symbol;
        this.userinfoUri = userinfoUri;
    }

    public SocialUserinfo extractUserInfo(Map<String, Object> userinfo) {
        return this.extractor.retrieveUserinfo(userinfo);
    }

    public String getUserinfoUri() {
        return userinfoUri;
    }
}
