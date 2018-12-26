package com.brainbackdoor.playgroundspring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PlaygroundSpring5Application {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundSpring5Application.class, args);
    }

}

