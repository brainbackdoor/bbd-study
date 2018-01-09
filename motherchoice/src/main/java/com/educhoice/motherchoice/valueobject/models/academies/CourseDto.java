package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.SpecialCourse;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public CourseDto(SpecialCourse specialCourse) {
        this.id = specialCourse.getCourseId();
        this.coursesClassification = Course.CoursesClassification.findBySpecifiedCourses(specialCourse.getCoursesClassification()).getSymbol();
        this.subjectClassification = specialCourse.getCoursesClassification().getSymbol();
        this.courseName = specialCourse.getTitle();
        this.grade = specialCourse.getGrades().getSymbol();
        this.tuition = specialCourse.getTuition();
        this.dayOfWeek = specialCourse.getDateTime();
    }

    public static List<CourseDto> generateCourseDtoFromAcademy(Academy academy) {
        return academy.getCourses().stream().map(c -> {
            return CourseDto.builder()
                    .courseName(c.getTitle())
                    .id(c.getCourseId())
                    .coursesClassification(Course.CoursesClassification.findBySpecifiedCourses(c.getCoursesClassification()).getSymbol())
                    .subjectClassification(c.getCoursesClassification().getSymbol())
                    .grade(c.getGrades().getSymbol())
                    .tuition(c.getTuition())
                    .dayOfWeek(c.getDateTime())
                    .build();
        }).collect(Collectors.toList());
    }
}
