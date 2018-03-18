package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.qna.Question;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    private String questionTitle;
    private String questionContent;
    private List<AnswerListDto> answer;

    public QuestionDto(Question question) {
        this.questionTitle = question.getTitle();
        this.questionContent = question.getContent();
        this.answer = question.getAnswers().stream().map(a -> new AnswerListDto(a)).collect(Collectors.toList());
    }

}
