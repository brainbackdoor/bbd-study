package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.models.persistent.repositories.QuestionRepository;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionListDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AcademyService academyService;

    public Question saveQuestion(QuestionPostDto questionRequest) {

        Question question = Question.builder()
                .title(questionRequest.getQuestionTitle())
                .content(questionRequest.getQuestionContent())
                .academies(academyService.findMultipleAcademiesById(questionRequest.getAcademies()))
                .build();

       return questionRepository.save(question);
    }

    public List<QuestionListDto> retrieveQuestionList(Account account) {
        return account.getQuestions().stream().map(q -> QuestionListDto.getDto(q)).collect(Collectors.toList());
    }
}
