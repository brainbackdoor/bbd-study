package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

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

}
