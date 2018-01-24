package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.valueobject.models.accounts.AccountJoinDto;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserJoinRequest {

    public enum JoinRequestType {
        PARENTS(0, req -> {
            return null;
        }),
        ACADEMY(1, req -> {
            return null;
        });

        private int code;
        private GenerateUserAction action;

        JoinRequestType(int code, GenerateUserAction action) {
            this.code = code;
            this.action = action;
        }

        public BasicAccount generateAccount(UserJoinRequest request) {
            return this.action.retrieveAccount(request);
        }

        @JsonValue
        public int getCode() {
            return this.code;
        }

    }

    private JoinRequestType requestType;
    private AccountJoinDto accountInfo;

}
