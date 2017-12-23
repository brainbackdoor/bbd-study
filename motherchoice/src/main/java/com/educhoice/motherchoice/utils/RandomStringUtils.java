package com.educhoice.motherchoice.utils;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomStringUtils {

    private final static int UPPERCASE_ALPHABET_BOUND = 90;

    public static String generateRandomString(int length) {


        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        IntStream.range(0, length).forEach(i -> {
            sb.append((char)(random.nextInt(UPPERCASE_ALPHABET_BOUND - 65) + 65));
        });

        return sb.toString();
    }
}
