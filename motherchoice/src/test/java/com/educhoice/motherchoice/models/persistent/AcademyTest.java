package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyTest {

    private static final Logger log = LoggerFactory.getLogger(AcademyTest.class);

    @Resource
    private AcademyRepository academyRepository;

    private List<Course> courses;
    private List<AcademyAddress> addresses = Lists.newArrayList();

}