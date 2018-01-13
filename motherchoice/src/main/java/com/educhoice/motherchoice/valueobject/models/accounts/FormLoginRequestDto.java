package com.educhoice.motherchoice.valueobject.models.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormLoginRequestDto {

    private String username;
    private String password;

}
