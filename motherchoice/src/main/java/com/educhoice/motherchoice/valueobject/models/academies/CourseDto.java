package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.DateTime;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CourseDto {

    private long id;
    private String coursesClassification;
    private String subjectClassification;
    private String courseName;
    private String grade;
    private long tuition;
    private List<DateTime> dayOfWeek;

}
