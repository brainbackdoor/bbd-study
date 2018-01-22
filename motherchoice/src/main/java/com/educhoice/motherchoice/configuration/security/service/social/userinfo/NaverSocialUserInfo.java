package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class NaverSocialUserInfo implements BasicSocialUserInfo {

    @JsonProperty("resultcode")
    private String resultcode;

    private String message;

    @JsonProperty("response")
    Map<String, String> userInfoMap;


    @Override
    public String getAccountName() {
        return userInfoMap.get("email");
    }

    @Override
    public String getUniqueId() {
        return userInfoMap.get("id");
    }

    @Override
    public String getNickname() {
        return userInfoMap.get("nickname");
    }

    @Override
    public String getProfileUri() {
        return userInfoMap.get("profile_image");
    }
}
