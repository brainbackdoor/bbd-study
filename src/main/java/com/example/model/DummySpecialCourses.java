package com.example.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DummySpecialCourses {
    private long specialCourseId;
    private String coursesClassification;
    private String subjectClassification;
    private String courseName;
    private String grade;
    private long tuition;
    private List<DateTime> dayOfWeek;
    private String duration;
}
