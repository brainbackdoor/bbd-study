package com.educhoice.motherchoice.valueobject.models.accounts;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.valueobject.models.academies.NewAcademyDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CorporateAccountJoinDto extends AccountJoinDto {

    private String phoneNo;
    private String originalName;

    @JsonProperty(value = "academy")
    private NewAcademyDto newAcademyInfo;

    public CorporateAccountJoinDto(String username, String password, String phoneNo, String originalName, NewAcademyDto newAcademyDto) {
        super(username, password, true, true, true);
        this.phoneNo = phoneNo;
        this.originalName = originalName;
        this.newAcademyInfo = newAcademyDto;
    }

    public CorporateAccount generateNewCorporateAccount() {
        return new CorporateAccount(super.getLoginId(), super.getPassword(), this.phoneNo, this.originalName, null, BasicAccount.AccountRoles.PRE_INSPECTION_USER);
    }

    public Academy generateNewAcademy() {
        return this.newAcademyInfo.generateNewAcademy();
    }

}
