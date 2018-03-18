package com.educhoice.motherchoice.valueobject.security.emailverification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailVerifiedDto {

    @JsonProperty(value = "isCertified")
    private boolean certified;

    @JsonProperty(value = "email")
    private String email;
}
