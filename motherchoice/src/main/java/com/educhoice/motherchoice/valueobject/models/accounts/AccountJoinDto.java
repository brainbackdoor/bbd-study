package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AccountJoinDto {

    private String mobileNo;
    private String userRealName;
    private String academyName;
    private String phoneNo;
    private String address;
    private String detailAddress;

}
