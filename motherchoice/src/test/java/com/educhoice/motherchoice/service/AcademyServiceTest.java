package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.utils.exceptions.entity.NoAcademyIdException;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.educhoice.motherchoice.valueobject.models.query.SearchableDateTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
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
import java.util.NoSuchElementException;

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
    private Academy testableAcademy;
    private Course course;
    private AcademyQueryDto dto;
    private AcademyDto academyDto;

    @Before
    @Transactional
    public void setUp() {

        SearchableDateTime dateTime = new SearchableDateTime();
        dateTime.setStartTime("12:00");
        dateTime.setEndTime("14:00");
        dateTime.setDay(Arrays.asList(DateTime.WeekDays.THU, DateTime.WeekDays.FRI, DateTime.WeekDays.SAT));

        this.course = Course.builder()
                .coursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.SCIENCE)
                .dateTime(Arrays.asList(new DateTime("13:00", "17:00", "목"), new DateTime("17:00", "18:00", "금")))
                .grades(Grades.SpecifiedGrades.ELEMENTARY_3)
                .title("클린코드 with Java")
                .build();

        this.academy = Academy.builder()
                .academyName("포비학원")
                .introduction("당신도 TDD 할 수 있다")
                .carAvailable(true)
                .address(AcademyAddress.builder().sido("경기도").sigungu("수원시 영통구").address("경기도 수원시 영통구 이의동").build())
                .courses(Arrays.asList(this.course))
                .build();

        this.dto = AcademyQueryDto.builder()
                .time(dateTime)
                .carAvailable(true)
                .grade(Grades.SpecifiedGrades.ELEMENTARY_3)
                .address("영통구 이의동")
                .subject("과학")
                .build();

        this.academyDto = AcademyDto.builder()
                .academyName("호눅스학원")
                .introduction("당신도 리눅스 잘 할 수 있다.")
                .build();


        academyRepository.deleteAll();
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
    public void 동적쿼리_학원찾기1() {
        academyService.saveAcademy(this.academy);

        List<Academy> academies = academyService.findMultipleAcademiesByQueryDto(this.dto);
        assertNotNull(academies.get(0));
        assertThat(academies.get(0).getAcademyName(), is("포비학원"));
    }

    @Test
    public void DTO로_검색결과_보여주기() throws Exception{
        academyService.saveAcademy(this.academy);
        List<AcademyDto> dtos = academyService.getAcademyDtos(this.dto);

        dtos.stream().forEach(d -> {
            try {
                log.debug(new ObjectMapper().writeValueAsString(d));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Test(expected = NoAcademyIdException.class)
    public void 학원정보_업데이트() {
        academyService.saveAcademy(this.academy);

        Academy academy = academyService.getAcademyByName("포비학원");

        this.academyDto.setId(academy.getAcademyId());

        academyService.updateAcademy(academyDto);
        assertNotNull(academyService.getAcademyByName("호눅스학원"));
        assertNull(academyService.getAcademyByName("포비학원"));

    }
}