package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class KakaoSocialUserInfo implements BasicSocialUserInfo {

    @JsonProperty("id")
    private long id;

    @JsonProperty("kaccount_email")
    private String email;

    @JsonProperty("kaccount_email_verified")
    private boolean emailVerified;

    private Map<String, String> properties;

    public boolean isEmailVerified() {
        return this.emailVerified;
    }

    @Override
    public String getAccountName() {
        return this.email;
    }

    @Override
    public Long getUniqueId() {
        return Long.valueOf(this.id);
    }

    @Override
    public String getNickname() {
        return this.properties.get("nickname");
    }

    @Override
    public String getProfileUri() {
        return this.properties.get("profile_image");
    }

    @Override
    public String toString() {
        return "KakaoSocialUserInfo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", properties=" + properties +
                '}';
    }
}
