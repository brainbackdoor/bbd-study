package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountJoinDto {

    private String mobileNo;
    private String userRealName;
    private String academyName;
    private String phoneNo;
    private String address;
    private String detailAddress;

}
