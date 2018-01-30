package com.educhoice.motherchoice.models.persistent.notifications;

import com.educhoice.motherchoice.models.persistent.repositories.notifications.NewQuestionStoreRepository;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewQuestionStoreTest {

    @Autowired
    private NewQuestionStoreRepository repository;

    private NewQuestionStore store;
    private Map<Long, NewQuestion> newQuestions = Maps.newHashMap();

    @Before
    public void setUp() {
        this.store = new NewQuestionStore();
        this.store.setCorporateAccountId(1L);

        NewQuestion newQuestion = new NewQuestion();
        newQuestion.setQuestionId(1L);
        newQuestion.setRead(false);

        this.newQuestions.put(1L, newQuestion);
        this.store.setQuestions(this.newQuestions);
    }

    @Test
    public void 몽고DB_저장() {
        this.repository.save(this.store);

        NewQuestionStore savedStore = this.repository.findByCorporateAccountId(1L).orElseThrow(() -> new NullPointerException());

        assertThat(savedStore.getQuestions().get(1L).isRead(), is(false));
        assertThat(savedStore.hasNewQuestion(), is(true));
    }
}