package com.educhoice.motherchoice.models.persistent.notifications;

import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.models.persistent.repositories.notifications.NewQuestionStoreRepository;
import com.educhoice.motherchoice.service.NewQuestionStoreService;
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

    @Autowired
    private NewQuestionStoreService service;

    private NewQuestionStore store;
    private Map<Long, NewQuestion> newQuestions = Maps.newHashMap();

    private Question question;
    private CorporateAccount account;

    @Before
    public void setUp() {
        this.store = new NewQuestionStore();
        this.store.setCorporateAccountId(1L);

        NewQuestion newQuestion = new NewQuestion();
        newQuestion.setQuestionId(1L);
        newQuestion.setRead(false);

        this.newQuestions.put(1L, newQuestion);
        this.store.setQuestions(this.newQuestions);

        this.question = Question.builder()
                .content("asdf")
                .title("asdf")
                .questionId(1L)
                .build();

        this.account = new CorporateAccount();
        this.account.setAccountId(1L);
    }

    @Test
    public void 몽고DB_저장() {
        this.repository.save(this.store);

        NewQuestionStore savedStore = this.repository.findByCorporateAccountId(1L).orElseThrow(() -> new NullPointerException());

        assertThat(savedStore.getQuestions().get(1L).isRead(), is(false));
        assertThat(savedStore.hasNewQuestion(), is(true));
    }

    @Test
    public void 읽음표시() {
        this.repository.save(this.store);

        service.readQuestion(this.account, this.question);
        assertThat(this.service.hasNewQuestions(this.account), is(false));

    }
}