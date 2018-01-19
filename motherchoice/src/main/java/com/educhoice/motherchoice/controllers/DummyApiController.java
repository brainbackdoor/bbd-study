package com.educhoice.motherchoice.controllers;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.configuration.security.service.AccountDetailsService;
import com.educhoice.motherchoice.configuration.security.service.UserJoinService;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.geolocation.Dong;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/exp")
@RestController
public class DummyApiController {

    @Autowired
    private UserJoinService service;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @GetMapping("/academy")
    @CrossOrigin
    public Academy returnDummyAcacdemy() {
        return Academy.builder()
                .address(AcademyAddress.builder().address("경기도 김포시 유현로 19").jibunAddress("경기도 김포시 풍무동 583").longitude(37.123123).latitude(127.3121321).build())
                .carAvailable(true)
                .academyName("포비학원")
                .courses(returnCourses())
                .build();

    }

    @GetMapping("/dong")
    @CrossOrigin
    public Dong returnDong() {
        return Dong.builder()
                .dongId(0)
                .dongName("풍무동")
                .juso("경기도 김포시")
                .build();
    }

    @PostMapping("/join/oauth")
    @PreAuthorize("hasRole('ROLE_OAUTH_TEMPORARY')")
    public IntegratedUserSigninToken socialJoinRequest(IntegratedUserSigninToken token, @RequestBody UserJoinRequest request) {
        service.joinSocialUser(token.getUserinfo(), request);

        return new IntegratedUserSigninToken((SecurityAccount)accountDetailsService.loadUserBySocialId(token.getUserinfo().getSocialId()));
    }

    private List<Course> returnCourses() {
        List<Course> courses = Lists.newArrayList();

        Course courseOne = new Course();
        courseOne.setDateTime(Arrays.asList(new DateTime("17:30","19:30","금")));
        courseOne.setTitle("TDD와 클린 코드 with Java");
        courseOne.setGrades(Grades.SpecifiedGrades.ELEMENTARY_6);
        courseOne.setCoursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.SCIENCE);
        courseOne.setCourseId(1L);

        courses.add(courseOne);

        return courses;


    }
}
