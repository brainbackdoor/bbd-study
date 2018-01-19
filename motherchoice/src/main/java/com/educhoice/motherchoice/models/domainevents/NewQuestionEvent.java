package com.educhoice.motherchoice.models.domainevents;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NewQuestionEvent extends ApplicationEvent{

    private List<CorporateAccount> destinations;
    private long questionId;

    public NewQuestionEvent(Question question) {
        super(question);
        this.destinations = question.getAcademies().stream().map(a -> a.getCorporateAccount()).collect(Collectors.toList());
        this.questionId = question.getQuestionId();
    }
}
