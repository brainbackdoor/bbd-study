package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

//
//public class PostListener implements ApplicationListener<PostPusblishedEvent> {
public class PostListener {

    @EventListener
    public void onApplicationEvent(PostPusblishedEvent event) {
        System.out.println("=============================");
        System.out.println(event.getPost().getTitle() + " is published!!");
        System.out.println("=============================");
    }
}
