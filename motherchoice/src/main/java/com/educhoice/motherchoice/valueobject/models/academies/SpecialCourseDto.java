package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.SpecialCourse;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SpecialCourseDto extends CourseDto{
    private String duration;

    public SpecialCourseDto(SpecialCourse specialCourse) {
        super(specialCourse);
        this.duration = specialCourse.getDuration();
    }

    public static List<SpecialCourseDto> generateDto(Academy academy) {
        return academy.getSpecialCourses().stream().map(s -> new SpecialCourseDto(s)).collect(Collectors.toList());
    }

}
