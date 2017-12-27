package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.utils.valueobject.security.UserJoinRequest;

@FunctionalInterface
public interface UserSignupAction {
    public BasicAccount userSignup(UserJoinRequest request);
}
