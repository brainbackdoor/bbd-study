package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties({"academyId, courses, specialCourses, academyName, ownerName, phoneNumber, introduction, carAvailable, address, events, images, corporateAccount, tags, questions"})
public class EmptyAcademy extends Academy {

    private String message;

    public String getMessage() {
        return this.message;
    }

}
