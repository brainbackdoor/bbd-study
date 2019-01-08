package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

public class PostPusblishedEvent extends ApplicationEvent {

    private final Post post;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PostPusblishedEvent(Object source) {
        super(source);
        this.post = (Post) source;
    }

    public Post getPost() {
        return post;
    }
}
