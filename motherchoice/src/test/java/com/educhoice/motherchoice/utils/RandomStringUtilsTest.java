package com.educhoice.motherchoice.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomStringUtilsTest {

	private static final Logger log = LoggerFactory.getLogger(RandomStringUtilsTest.class);

    @Autowired
    private RandomStringUtils randomStringUtils;

    @Test
    public void 랜덤문자열_잘튀어나오는지() {
    	log.debug("랜덤 문자열 : {}", randomStringUtils.generateRandomString(50));
    }

}