package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAcademyDto {

    @JsonProperty(value = "academyName")
    private String name;

    @JsonProperty(value = "ownerName")
    private String ownerName;

    @JsonProperty(value = "academyPhoneNumber")
    private String phoneNumber;

    @JsonProperty(value = "address")
    private AcademyAddress academyAddress;

    public Academy generateNewAcademy() {
        return Academy.builder()
                .academyName(this.name)
                .ownerName(this.ownerName)
                .phoneNumber(this.phoneNumber)
                .address(this.academyAddress)
                .build();
    }

}
