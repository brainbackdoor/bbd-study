package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcademyTaggingDto {

    private long academyId;
    private String academyName;
    private AcademyAddress address;

    public AcademyTaggingDto(Academy academy) {
        this.academyId = academy.getAcademyId();
        this.academyName = academy.getAcademyName();
        this.address = academy.getAddress().get(0);
    }

    public static AcademyTaggingDto getDto(Academy academy) {
        return new AcademyTaggingDto(academy);
    }

}
