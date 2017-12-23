package com.educhoice.motherchoice.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomStringUtilsTest {

    @Autowired
    private RandomStringUtils randomStringUtils;

    @Test
    public void 랜덤문자열_잘튀어나오는지() {
        System.out.println(randomStringUtils.generateRandomString(50));
    }

}