package com.educhoice.motherchoice.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneralBeans {

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
