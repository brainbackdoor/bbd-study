package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.qna.Answer;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.models.persistent.repositories.QuestionRepository;
import com.educhoice.motherchoice.models.persistent.repositories.AnswerRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AnswerDetailsDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AnswerPostDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionListDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AcademyService academyService;
    
    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    public Question saveQuestion(QuestionPostDto questionRequest) {

        Question question = Question.builder()
                .title(questionRequest.getQuestionTitle())
                .content(questionRequest.getQuestionContent())
                .build();
        
        if(questionRequest.getAcademies() != null) {
            question.setAcademies(academyService.findMultipleAcademiesById(questionRequest.getAcademies()));
        }

       return questionRepository.save(question);
    }
    public Answer saveAnswer(AnswerPostDto answerRequest) {
        Answer answer = Answer.builder()
                        .account(corporateAccountRepository.findOne(answerRequest.getAccountId()))
                        .content(answerRequest.getAnswerContent())
                        .question(questionRepository.findOne(answerRequest.getQuestionId()))
                        .build();
        return answerRepository.save(answer);
    }

    public List<QuestionListDto> retrieveQuestionList(Account account) {
        return account.getQuestions().stream().map(q -> QuestionListDto.getDto(q)).collect(Collectors.toList());
    }

    public QuestionDto retrieveQuestion(long questionId) {
        return new QuestionDto(questionRepository.findOne(questionId));
    }

    public AnswerDetailsDto retrieveAnswer(long answerId) {
        return new AnswerDetailsDto(answerRepository.findOne(answerId));
    }

}
