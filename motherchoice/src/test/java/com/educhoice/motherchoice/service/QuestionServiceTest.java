package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionPostDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    private QuestionPostDto question;

    @Before
    public void setUp() {
        this.question = new QuestionPostDto();
        this.question.setQuestionTitle("fuck you!");
        this.question.setQuestionContent("blablabla");
    }

    @Test
    public void 질문저장() {
        questionService.saveQuestion(this.question);
    }

}