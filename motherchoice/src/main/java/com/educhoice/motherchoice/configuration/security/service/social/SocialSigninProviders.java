package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.KakaoSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.NaverSocialUserInfo;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SocialSigninProviders {

    KAKAO("kakao", "https://kapi.kakao.com/v1/user/me", KakaoSocialUserInfo.class),
    NAVER("naver", "https://openapi.naver.com/v1/nid/me", NaverSocialUserInfo.class),

    TEST("test", "http://www.naver.com", null);


    private String symbol;
    private String userinfoUri;
    private Class<? extends BasicSocialUserInfo> dtoClass;

    SocialSigninProviders(String symbol, String userinfoUri, Class<? extends BasicSocialUserInfo> dtoClass){
        this.symbol = symbol;
        this.userinfoUri = userinfoUri;
        this.dtoClass = dtoClass;
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }

    public String getUserinfoUri() {
        return userinfoUri;
    }

    public Class<? extends BasicSocialUserInfo> getUserinfoDtoClass() {
        return this.dtoClass;
    }
}
