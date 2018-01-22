package com.educhoice.motherchoice.valueobject.models.accounts;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialAuthinfoDto {

    private SocialSigninProviders provider;
    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserinfoRestResource() {
        return this.provider.getUserinfoUri();
    }

    public Class<? extends BasicSocialUserInfo> getUserinfoMetaclass() {
        return this.provider.getUserinfoDtoClass();
    }
}
