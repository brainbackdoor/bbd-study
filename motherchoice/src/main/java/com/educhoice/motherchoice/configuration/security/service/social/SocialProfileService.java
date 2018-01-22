package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.service.UserJoinService;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.utils.AuthHttpRequestService;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocialProfileService {

    @Autowired
    private UserJoinService userJoinService;

    @Autowired
    private AuthHttpRequestService requestService;

    //TODO token refresh logic - new social user join logic.

    public void refresh(BasicAccount account) {

    }

    public void generateNewUser(SocialAuthinfoDto dto) {


    }
}
