package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.valueobject.models.accounts.AccountJoinDto;
import com.educhoice.motherchoice.valueobject.models.accounts.CorporateAccountJoinDto;
import com.educhoice.motherchoice.valueobject.models.accounts.ParentsAccountJoinDto;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserJoinRequest {

    public enum JoinRequestType {
        PARENTS(0, req -> {
             ParentsAccountJoinDto dto = (ParentsAccountJoinDto)req.getAccountInfo();
             return dto.generateAccount();
        }),
        ACADEMY(1, req -> {
            CorporateAccountJoinDto dto = (CorporateAccountJoinDto)req.getAccountInfo();
            return dto.generateNewCorporateAccount();
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

        public boolean isAcademyRequest() {
            return this == ACADEMY;
        }
    }

    @JsonProperty(value = "joinType")
    private JoinRequestType requestType;

    @JsonProperty(value = "account")
    private AccountJoinDto accountInfo;

    @JsonProperty(value = "accessToken")
    private SocialAuthinfoDto socialAuthinfoDto;

    @JsonIgnore
    public boolean isCorporateAccountRequest() {
        return this.requestType.isAcademyRequest();
    }

    @JsonIgnore
    public boolean isSocialRequest() {
        return this.socialAuthinfoDto != null;
    }

    @JsonIgnore
    public String getJoinEmail() {
        return this.accountInfo.getLoginId();
    }

    public BasicAccount generateAccount() {
        return this.requestType.generateAccount(this);
    }

    public Optional<Academy> generateAcademyInfo() {
        if (!this.requestType.isAcademyRequest() || !(this.accountInfo instanceof CorporateAccountJoinDto)) {
            return Optional.empty();
        }
        CorporateAccountJoinDto dto = (CorporateAccountJoinDto)this.accountInfo;
        return Optional.ofNullable(dto.generateNewAcademy());
    }

    @JsonIgnore
    public SocialAuthinfoDto getAuthinfoDto() {
        return this.socialAuthinfoDto;
    }

    public void setSocialAuthinfoDto(SocialAuthinfoDto dto) {
        this.socialAuthinfoDto = dto;
    }

}
