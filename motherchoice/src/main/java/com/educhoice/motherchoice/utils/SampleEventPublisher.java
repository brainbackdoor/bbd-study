package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SampleEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedRate = 1000L)
    public void sendFakeEvent() {
        NewQuestionEvent event = new NewQuestionEvent(Question.builder().content("fuck you!").writer(Account.builder().build()).build());

        this.eventPublisher.publishEvent(event);
    }
}
