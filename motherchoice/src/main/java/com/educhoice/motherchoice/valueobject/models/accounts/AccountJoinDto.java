package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountJoinDto {

    private String loginId;
    private String password;
    private boolean terms;
    private boolean privacy;
    private boolean marketingInfo;

}
