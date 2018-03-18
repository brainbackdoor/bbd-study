package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.qna.Question;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionListDto {

    private long questionId;
    private String questionTitle;
    private String created;
    private int questionSize;
    private int answerSize;
    private int additionalAnswerSize;
    private boolean unRead;

    public QuestionListDto(Question question) {
        this.questionId = question.getQuestionId();
        this.questionTitle = question.getTitle();
        this.created = question.getFormattedCreatedTime();
        this.questionSize = question.getQuestionedAcademyCount();
        this.answerSize = question.getAnswersCount();
        this.additionalAnswerSize = question.getRepliesCount();

        //TODO write unread determining logic.
        this.unRead = false;

    }

    public static QuestionListDto getDto(Question question) {
        return new QuestionListDto(question);
    }

}
