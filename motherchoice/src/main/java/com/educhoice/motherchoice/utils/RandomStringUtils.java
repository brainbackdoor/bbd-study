package com.educhoice.motherchoice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class RandomStringUtils {

    private final static int UPPERCASE_ALPHABET_STARTING_POINT = 65;
    private final static int UPPERCASE_ALPHABET_BOUND = 90;
    private final static int UPPERCASE_ALPHABET_INT_RANGE = UPPERCASE_ALPHABET_BOUND - UPPERCASE_ALPHABET_STARTING_POINT;

    @Autowired
    private Random random;

    public String generateRandomString(int length) {

        StringBuilder sb = new StringBuilder();


        IntStream.range(0, length).forEach(i -> {
            sb.append((char)(random.nextInt(UPPERCASE_ALPHABET_INT_RANGE) + UPPERCASE_ALPHABET_STARTING_POINT));
        });

        return sb.toString();
    }
}
