package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.educhoice.motherchoice.valueobject.models.query.SearchableDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AcademyServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AcademyServiceTest.class);

    @Autowired
    private AcademyService academyService;

    @Autowired
    private AcademyRepository academyRepository;

    private Academy academy;
    private Course course;
    private AcademyQueryDto dto;

    @Before
    public void setUp() {

        SearchableDateTime dateTime = new SearchableDateTime();
        dateTime.setStartTime("12:00");
        dateTime.setEndTime("14:00");
        dateTime.setDay(Arrays.asList(DateTime.WeekDays.THU));

        this.course = Course.builder()
                .coursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.SCIENCE)
                .dateTime(Arrays.asList(new DateTime("13:00", "17:00", "목")))
                .grades(Grades.SpecifiedGrades.ELEMENTARY_3)
                .title("클린코드 with Java")
                .build();

        this.academy = Academy.builder()
                .academyName("포비학원")
                .introduction("당신도 TDD 할 수 있다")
                .carAvailable(true)
                .courses(Arrays.asList(this.course))
                .build();

        this.dto = AcademyQueryDto.builder()
                .time(dateTime)
                .carAvailable(true)
                .build();

        academyRepository.deleteAll();
    }

    @Test
    @Transactional
    public void SearchAcademyByCriteria() {
        academyService.saveAcademy(this.academy);

        log.debug(academyRepository.findAcademyIdCriteria(2).get().toString());
        assertTrue(academyRepository.findAcademyIdCriteria(2).isPresent());

    }

    @Test
    @Transactional
    public void Criteria로_학원찾기_이름() {
        academyService.saveAcademy(this.academy);
        Academy academy = academyService.getAcademyByName("포비학원");
        assertTrue(academy.getAcademyId() == 1L);
    }

    @Test
    @Transactional
    public void 강의시간기반_학원찾기() {
        academyService.saveAcademy(this.academy);

        Academy academy = academyRepository.findByCourses_DateTime_StartTimeBetweenAndCourses_DateTime_EndTimeLessThan(LocalTime.parse("12:00"), LocalTime.parse("18:00"), LocalTime.parse("18:00")).get(0);

        assertNotNull(academy);
    }

    @Test
    @Transactional
    public void 동적쿼리_학원찾기1() {
        academyService.saveAcademy(this.academy);

        List<Academy> academies = academyService.findMultipleAcademiesByQueryDto(this.dto);
        assertNotNull(academies.get(0));
        assertThat(academies.get(0).getAcademyName(), is("포비학원"));
    }

}