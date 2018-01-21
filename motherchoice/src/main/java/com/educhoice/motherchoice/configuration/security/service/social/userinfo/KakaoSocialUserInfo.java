package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoSocialUserInfo implements BasicSocialUserInfo {

    private int id;

    @JsonProperty("kaccount_email")
    private String email;

    @JsonProperty("kaccount_email_verified")
    private boolean emailVerified;

    private Map<String, String> properties;

    @Override
    public String getAccountName() {
        return this.email;
    }

    @Override
    public String getUniqueId() {
        return String.valueOf(this.id);
    }

    @Override
    public String getNickname() {
        return this.properties.get("nickname");
    }

    @Override
    public String getProfileUri() {
        return this.properties.get("profile_image");
    }
}
