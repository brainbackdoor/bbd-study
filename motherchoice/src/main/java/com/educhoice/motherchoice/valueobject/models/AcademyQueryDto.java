package com.educhoice.motherchoice.valueobject.models;

import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades.SpecifiedGrades;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter

@JsonRootName("queryStore")
@ToString
public class AcademyQueryDto {

    private SearchableDateTime time;
    private SpecifiedGrades grade;
    private String subject;
    private Location location;
    private boolean carAvailable;

}
