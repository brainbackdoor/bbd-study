package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
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
            return new Account(req.getLoginId(), req.getPassword(), req.getNickname(), req.getMemberAddress(), req.getProfileUri(), req.isMarketingAllowed(), BasicAccount.AccountRoles.UNPAID_USER);
        }),
        ACADEMY(1, req -> {
            return new CorporateAccount(req.getLoginId(), req.getPassword(), req.getOriginalName(), req.getPhoneNo(), null, BasicAccount.AccountRoles.PRE_INSPECTION_USER);
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

    private String loginId;
    private String password;
    private String nickname;
    private String memberAddress;
    private String originalName;
    private String phoneNo;
    private String profileUri;
    private boolean marketingAllowed;
    private JoinRequestType joinType;

    public BasicAccount generateAccount() {
        return this.joinType.generateAccount(this);
    }

    public boolean isCorporateJoinRequest() {
        return this.joinType == JoinRequestType.ACADEMY;
    }

    public void setAttributesFromSocialInfo(SocialUserinfo info) {
        this.loginId = info.getLoginId();
        this.profileUri = info.getProfileUri();
    }
}
