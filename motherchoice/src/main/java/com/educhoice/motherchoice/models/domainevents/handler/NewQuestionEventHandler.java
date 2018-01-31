package com.educhoice.motherchoice.models.domainevents.handler;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import com.educhoice.motherchoice.models.persistent.notifications.NewQuestion;
import com.educhoice.motherchoice.models.persistent.notifications.NewQuestionStore;
import com.educhoice.motherchoice.models.persistent.repositories.notifications.NewQuestionStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewQuestionEventHandler implements ApplicationListener<NewQuestionEvent> {

    private static final Logger log = LoggerFactory.getLogger(NewQuestionEventHandler.class);

    @Autowired
    private NewQuestionStoreRepository repository;

    @Override
    public void onApplicationEvent(NewQuestionEvent event) {
//        log.info("new question has registered! event handler now registering new question");
//        List<Long> corporateAccountIds = event.getDestinations().stream().map(c -> c.getAccountId()).collect(Collectors.toList());
//
//        List<NewQuestionStore> stores = corporateAccountIds.stream()
//                .filter(id -> repository.findByCorporateAccountId(id).isPresent())
//                .peek(id -> corporateAccountIds.remove(id))
//                .map(id -> repository.findByCorporateAccountId(id).get()).collect(Collectors.toList());
//
//        stores.stream().peek(s -> s.addNewQuestion(new NewQuestion(event))).forEach(s -> repository.save(s));
//
//        corporateAccountIds.stream()
//                .forEach(id -> repository.save(new NewQuestionStore(id, event)));

    }
}
