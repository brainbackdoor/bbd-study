package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.qna.Answer;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonRootName("answer")
public class AnswerListDto {

    private int answerId;
    private String academyName;
    private String created;
    private boolean unRead;

    public AnswerListDto(Answer answer) {
        this.answerId = (int)answer.getId();
        this.academyName = answer.getAcademyName();
        this.created = answer.getFormattedCreatedTime();
        this.unRead = false;
    }

}
