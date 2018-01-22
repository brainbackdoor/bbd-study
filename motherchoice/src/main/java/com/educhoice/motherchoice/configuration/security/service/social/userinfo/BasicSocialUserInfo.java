package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

public interface BasicSocialUserInfo {

    String getAccountName();

    default String getPassword() {
        return "1234";
    }

    Long getUniqueId();
    String getNickname();
    String getProfileUri();

}
