package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;

@FunctionalInterface
public interface UserSignupAction {
    public BasicAccount userSignup(UserJoinRequest request);
}
