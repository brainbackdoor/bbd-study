package com.educhoice.motherchoice.models.domainevents.handler;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import com.educhoice.motherchoice.utils.SseEmitterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SampleEventHandler {

    private static final Logger log = LoggerFactory.getLogger(SampleEventHandler.class);

    @Autowired
    private SseEmitterManager manager;

    @EventListener
    public void listenEvent(NewQuestionEvent event) {
        log.debug("Question Event Caught!");


    }
}
