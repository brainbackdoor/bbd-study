package com.brainbackdoor.playgroundspring3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class PlaygroundSpring3Application {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundSpring3Application.class, args);
    }

}

