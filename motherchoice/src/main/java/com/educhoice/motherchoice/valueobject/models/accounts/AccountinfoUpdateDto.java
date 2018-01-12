package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountinfoUpdateDto {

    private String accountId;
    private String password;
    private String mobileNo;
    private boolean marketingInfo;
}
