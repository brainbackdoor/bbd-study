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
public class DummyCourses {
    private long courseId;
    private String coursesClassification;
    private String subjectClassification;
    private String courseName;
    private String grade;
    private long tuition;
    private List<DateTime> dayOfWeek;
}
