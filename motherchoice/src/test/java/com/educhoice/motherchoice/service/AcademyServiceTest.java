package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Before
    public void setUp() {
        this.academy = Academy.builder()
                .academyName("포비학원")
                .introduction("당신도 TDD 할 수 있다")
                .carAvailable(false)
                .build();

    }

    @Test
    @Transactional
    public void SearchAcademyByCriteria() {
        academyService.saveAcademy(this.academy);

        log.debug(academyRepository.findAcademyIdCriteria(1).get().toString());
        assertTrue(academyRepository.findAcademyIdCriteria(1).isPresent());

    }

}