package com.educhoice.motherchoice.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStringUtilsTest {

    @Test
    public void 랜덤문자열_잘튀어나오는지() {
        System.out.println(RandomStringUtils.generateRandomString(50));
    }

}