package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;

@FunctionalInterface
public interface UserSignupAction {
    BasicAccount userSignup(UserJoinRequest request);
}
