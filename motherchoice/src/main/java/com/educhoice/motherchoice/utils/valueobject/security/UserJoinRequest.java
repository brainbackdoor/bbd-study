package com.educhoice.motherchoice.utils.valueobject.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {

    private String email;
    private String password;


    public enum JoinRequestTypes {
        PARENTS,
        CORPORATE;
    }

}
