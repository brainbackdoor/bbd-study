package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.KakaoSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.NaverSocialUserInfo;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SocialSigninProviders {

    KAKAO("kakao", "https://kapi.kakao.com/v1/user/me", new KakaoSocialUserInfo()),
    NAVER("naver", "https://openapi.naver.com/v1/nid/me", new NaverSocialUserInfo()),

    TEST("test", "http://www.naver.com", null);


    private String symbol;
    private String userinfoUri;
    private BasicSocialUserInfo userinfoDto;

    SocialSigninProviders(String symbol, String userinfoUri, BasicSocialUserInfo userinfoDto){
        this.symbol = symbol;
        this.userinfoUri = userinfoUri;
        this.userinfoDto = userinfoDto;
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
