package com.educhoice.motherchoice.valueobject.models.accounts;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public String getUserinfoRestResource() {
        return this.provider.getUserinfoUri();
    }

    @JsonIgnore
    public Class<? extends BasicSocialUserInfo> getUserinfoMetaclass() {
        return this.provider.getUserinfoDtoClass();
    }
}
