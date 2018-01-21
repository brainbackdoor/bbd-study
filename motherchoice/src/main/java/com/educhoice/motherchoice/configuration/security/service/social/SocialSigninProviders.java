package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.configuration.security.service.SocialUserinfoExtractor;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.KakaoSocialUserInfo;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public enum SocialSigninProviders {

    KAKAO(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("properties");
                int socialId = (int)userinfo.get("id");
                return SocialUserinfo.builder().loginId((String)properties.get("kaccount_email")).socialId(socialId).nickname((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "kakao", "https://kapi.kakao.com/v1/user/me", new KakaoSocialUserInfo()),
    NAVER(
            (userinfo -> {
                Map<String, Object> properties = (Map<String, Object>)userinfo.get("response");
                return SocialUserinfo.builder().loginId((String)properties.get("loginId")).socialId(Integer.parseInt((String)properties.get("id"))).nickname((String)properties.get("nickname")).profileUri((String)properties.get("profile_image")).build();
            }), "naver", "https://openapi.naver.com/v1/nid/me", null),

    TEST(
            null, "test", "http://www.naver.com", null
    );

    private SocialUserinfoExtractor extractor;
    private String symbol;
    private String userinfoUri;
    private BasicSocialUserInfo userinfoDto;

    SocialSigninProviders(SocialUserinfoExtractor extractor, String symbol, String userinfoUri, BasicSocialUserInfo userinfoDto){
        this.extractor = extractor;
        this.symbol = symbol;
        this.userinfoUri = userinfoUri;
        this.userinfoDto = userinfoDto;
    }

    public SocialUserinfo extractUserInfo(Map<String, Object> userinfo) {
        return this.extractor.retrieveUserinfo(userinfo);
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }

    public String getUserinfoUri() {
        return userinfoUri;
    }

    public Class<? extends BasicSocialUserInfo> getUserinfoDtoClass() {
        return this.userinfoDto.getClass();
    }
}
