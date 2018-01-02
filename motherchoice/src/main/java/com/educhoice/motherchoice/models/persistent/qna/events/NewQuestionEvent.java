package com.educhoice.motherchoice.models.persistent.qna.events;

import org.springframework.context.ApplicationEvent;

public class NewQuestionEvent extends ApplicationEvent {

    public NewQuestionEvent(Object source) {
        super(source);
    }
}
