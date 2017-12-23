package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.geolocation.Dong;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyTest {

    private static final Logger log = LoggerFactory.getLogger(AcademyTest.class);

    @Resource
    private AcademyRepository academyRepository;

    private List<Course> courses;
    private List<AcademyAddress> addresses = Lists.newArrayList();
    private Dong dong;

    @Before
    public void setUp() {
        this.courses = Lists.newArrayList();
        Course expCourse = new Course();
        expCourse.setTitle("Test 주도 개발");
        expCourse.setGrades(Grades.SpecifiedGrades.ELEMENTARY_6);
        expCourse.setDateTime(Arrays.asList(new DateTime("12:30", "15:30", "월")));
        this.courses.add(expCourse);
        AcademyAddress academyAddress = new AcademyAddress("경기도 김포시 유현로 19", "경기도 김포시 유현로 19", "경기도 김포시 풍무동", "10120", "경기도", "김포시");
        this.addresses.add(academyAddress);
        System.out.println(this.addresses);
        this.dong = new Dong();
    }

    @Test
    public void 학원정보_입력() {
        Academy academy = new Academy();
        academy.setAcademyName("포비학원");
        academy.setAddress(Arrays.asList(new AcademyAddress("경기도 김포시 유현로 19", "경기도 김포시 유현로 19", "경기도 김포시 풍무동", "10120", "경기도", "김포시")));
        academy.setCourses(this.courses);
//        System.out.println(academy.getAddress().size());

        this.academyRepository.save(academy);
        Academy testableAcademy = this.academyRepository.findOne(1L);
        System.out.println(testableAcademy.toString());
        assertNotNull(this.addresses);
        assertEquals("포비학원", testableAcademy.getAcademyName());
        assertNotNull(testableAcademy.getAddress());

        testableAcademy.getAddress().stream().forEach(System.out::println);
    }


}