package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsernameInUseDto {

    private String nickname;
    private boolean isUsed;
}
