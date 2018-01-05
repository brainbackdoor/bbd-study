package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.educhoice.motherchoice.configuration.security.entity.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;

public class TemporarySocialAccount extends BasicAccount{

    private static final String SOCIAL_TEMPORARY_PWD = "1111";

    private SocialUserinfo userinfo;

    public TemporarySocialAccount(SocialUserinfo userinfo) {
        super(userinfo.getUsername(), SOCIAL_TEMPORARY_PWD, userinfo.getProfileUri(), AccountRoles.OAUTH_TEMPORARY_USER);
        this.userinfo = userinfo;
    }

    public SocialUserinfo getUserinfo() {
        return userinfo;
    }
}
