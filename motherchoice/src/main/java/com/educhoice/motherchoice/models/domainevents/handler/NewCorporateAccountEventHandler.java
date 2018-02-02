package com.educhoice.motherchoice.models.domainevents.handler;

import com.educhoice.motherchoice.models.domainevents.NewCorporateAccountEvent;
import com.educhoice.motherchoice.service.NewQuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewCorporateAccountEventHandler implements ApplicationListener<NewCorporateAccountEvent>{

    @Autowired
    private NewQuestionStoreService service;

    @Override
    public void onApplicationEvent(NewCorporateAccountEvent event) {
        service.saveNewQuestionStore(event.getAccountId());
    }
}
