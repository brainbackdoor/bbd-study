package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;

@FunctionalInterface
public interface GenerateUserAction {

    BasicAccount retrieveAccount(UserJoinRequest req);
}
