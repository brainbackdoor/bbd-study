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

    private long courseId;
    private String coursesClassification;
    private String subjectClassification;
    private String courseName;
    private String grade;
    private long tuition;
    private List<DateTime> dayOfWeek;

    public CourseDto(SpecialCourse specialCourse) {
        this.courseId = specialCourse.getCourseId();
        this.coursesClassification = Course.CoursesClassification.findBySpecifiedCourses(specialCourse.getCoursesClassification()).getSymbol();
        this.subjectClassification = specialCourse.getCoursesClassification().getSymbol();
        this.courseName = specialCourse.getTitle();
        this.grade = specialCourse.getGrades().getSymbol();
        this.tuition = specialCourse.getTuition();
        this.dayOfWeek = specialCourse.getDateTime();
    }

    public Course getCourseEntity() {
        return Course.builder()
                .courseId(this.courseId)
                .coursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.getSpecifiedCoursesClassificationBySymbol(this.subjectClassification))
                .title(this.courseName)
                .tuition(this.tuition)
                .dateTime(this.dayOfWeek)
                .build();
    }

    public static List<CourseDto> generateCourseDtoFromAcademy(Academy academy) {
        return academy.getCourses().stream().map(c -> {
            return CourseDto.builder()
                    .courseName(c.getTitle())
                    .courseId(c.getCourseId())
                    .coursesClassification(Course.CoursesClassification.findBySpecifiedCourses(c.getCoursesClassification()).getSymbol())
                    .subjectClassification(c.getCoursesClassification().getSymbol())
                    .grade(c.getGrades().getSymbol())
                    .tuition(c.getTuition())
                    .dayOfWeek(c.getDateTime())
                    .build();
        }).collect(Collectors.toList());
    }
}
