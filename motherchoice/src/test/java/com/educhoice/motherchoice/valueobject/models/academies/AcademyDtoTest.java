package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.DateTime;
import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.service.AcademyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AcademyDtoTest {

    @Autowired
    private AcademyService academyService;

    private static final Logger log = LoggerFactory.getLogger(AcademyDtoTest.class);
    private Academy academy;
    private CorporateAccount account;

    @Before
    public void setUp() {
        this.account = new CorporateAccount("dasolhannah@gmail.com", "1234");

        this.academy = Academy.builder()
                .academyId(1)
                .courses(Arrays.asList(Course.builder().title("TDD와 Clean Code with Java").coursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.SCIENCE).grades(Grades.SpecifiedGrades.ELEMENTARY_3).tuition(100000L).dateTime(Arrays.asList(new DateTime("13:00", "18:00", "토"))).build()))
                .academyName("포비학원")
                .address(AcademyAddress.builder().address("경기도 수원시 영통구 이의동").roadAddress("경기도 수원시 포은대로 123").sido("경기도").sigungu("수원시 영통구").build())
                .carAvailable(true)
                .introduction("당신도 포비처럼 코딩할 수 있다")
                .corporateAccount(this.account)
                .build();

        academyService.saveAcademy(this.academy);
    }

    @Test
    @Transactional
    public void DTO객체_잘만들어지는지() {
        Academy academy = academyService.getAcademyById(1);

        AcademyDto dto = AcademyDto.generateAcademyDto(academy);

        try {
            log.debug(new ObjectMapper().writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assertEquals("포비학원", dto.getAcademyName());
    }

}