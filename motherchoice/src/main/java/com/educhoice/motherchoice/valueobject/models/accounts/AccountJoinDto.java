package com.educhoice.motherchoice.valueobject.models.accounts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "requestType")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "parents", value = ParentsAccountJoinDto.class),
        @JsonSubTypes.Type(name = "corporate", value = CorporateAccountJoinDto.class)
})
public class AccountJoinDto {

    private String loginId;
    private String password;
    private boolean terms;
    private boolean privacy;
    private boolean marketingInfo;

}
