package com.educhoice.motherchoice.valueobject.models.accounts;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParentsAccountJoinDto extends AccountJoinDto {

    @JsonProperty(value = "memberAddress")
    private String address;

    @JsonProperty(value = "nickname")
    private String nickname;

    public Account generateAccount() {
        return new Account(super.getLoginId(), super.getPassword(), this.nickname, this.address, null, super.isMarketingInfo(), BasicAccount.AccountRoles.UNPAID_USER);
    }

}
