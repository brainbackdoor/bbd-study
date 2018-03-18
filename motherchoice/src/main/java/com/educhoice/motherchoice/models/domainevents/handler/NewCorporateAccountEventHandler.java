package com.educhoice.motherchoice.models.domainevents.handler;

import com.educhoice.motherchoice.models.domainevents.NewCorporateAccountEvent;
import com.educhoice.motherchoice.service.NewQuestionStoreService;
import com.educhoice.motherchoice.utils.aws.S3ControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewCorporateAccountEventHandler implements ApplicationListener<NewCorporateAccountEvent>{
    private static final Logger log = LoggerFactory.getLogger(NewCorporateAccountEventHandler.class);

    @Autowired
    private NewQuestionStoreService service;

    @Autowired
    private S3ControlService s3ControlService;

    @Override
    public void onApplicationEvent(NewCorporateAccountEvent event) {
        log.debug("new corporate account has made! event handler now do dirty things.");
        service.saveNewQuestionStore(event.getAccountId());
    }
}
