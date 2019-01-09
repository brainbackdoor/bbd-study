package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {
//    @Bean
//    public PostListener postListener() {
//        return new PostListener();
//    }

    @Bean
    public ApplicationListener<PostPusblishedEvent> postListener() {
        return event -> {
            System.out.println("=============================");
            System.out.println(event.getPost().getTitle() + " is published!!");
            System.out.println("=============================");
        };
    }
}
