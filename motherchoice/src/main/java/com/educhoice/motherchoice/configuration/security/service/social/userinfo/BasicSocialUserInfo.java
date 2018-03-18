package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

public interface BasicSocialUserInfo {

    String getAccountName();

    default String getPassword() {
        return "1234";
    }

    Long getUniqueId();
    String getEmail();
    String getNickname();
    String getProfileUri();

}
