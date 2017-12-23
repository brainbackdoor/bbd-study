package com.educhoice.motherchoice.controllers;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.geolocation.Dong;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/exp")
@RestController
public class DummyApiController {

    @GetMapping("/academy")
    @CrossOrigin
    public Academy returnDummyAcacdemy() {
        Academy academy = new Academy();
        academy.setAddress(Arrays.asList(new AcademyAddress("경기도 김포시 유현로 19", "경기도 김포시 유현로 19", "경기도 김포시 풍무동 683", "10120", "경기도", "김포시")));
        academy.setCarAvailable(true);
        academy.setAcademyName("포비학원");
        academy.setCourses(returnCourses());

        return academy;
    }

    private List<Course> returnCourses() {
        List<Course> courses = Lists.newArrayList();

        Course courseOne = new Course();
        courseOne.setDateTime(Arrays.asList(new DateTime("17:30","19:30","금")));
        courseOne.setTitle("TDD와 클린 코드 with Java");
        courseOne.setGrades(Grades.SpecifiedGrades.ELEMENTARY_6);
        courseOne.setCourseId(1L);

        courses.add(courseOne);
        return courses;
    }
}
