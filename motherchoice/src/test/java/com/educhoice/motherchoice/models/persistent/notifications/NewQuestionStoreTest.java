package com.educhoice.motherchoice.models.persistent.notifications;

import com.educhoice.motherchoice.models.persistent.repositories.notifications.NewQuestionStoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewQuestionStoreTest {

    @Autowired
    private NewQuestionStoreRepository repository;

    private NewQuestionStore store;
    private Map<Long, NewQuestion> newQuestions;

    @Before
    public void setUp() {
        this.store = new NewQuestionStore();
        this.store.setCorporateAccountId(1L);

        NewQuestion newQuestion = new NewQuestion();
        newQuestion.setQuestionId(1L);
        newQuestion.setRead(false);

        this.newQuestions.put(1L, newQuestion);
    }

    @Test
    public void 몽고DB_저장() {
        this.repository.save(this.store);
    }
}