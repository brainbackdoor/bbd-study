package com.educhoice.motherchoice.valueobject.models.query;

import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.Grades.SpecifiedGrades;
import com.educhoice.motherchoice.models.persistent.Course.CoursesClassification.SpecifiedCoursesClassification;
import com.educhoice.motherchoice.valueobject.models.Location;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@JsonRootName("queryStore")
@ToString
public class AcademyQueryDto {

    private SearchableDateTime time;
    private SpecifiedGrades grade;
    private String subject;
    private String address;
    private boolean carAvailable;

    public SpecifiedCoursesClassification generateSpecifiedCourse() {
        return SpecifiedCoursesClassification.getSpecifiedCoursesClassificationBySymbol(this.subject);
    }

    public Course.CoursesClassification generateGeneralCourse() {
        return Course.CoursesClassification.getCoursesClassificationBySymbol(this.subject);
    }

}
