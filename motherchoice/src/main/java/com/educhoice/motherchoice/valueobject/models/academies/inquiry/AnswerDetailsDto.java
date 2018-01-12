package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.qna.Answer;

import java.util.List;

public class AnswerDetailsDto {

    private long answerId;
    private String academyName;
    private String created;
    private String content;
    private List<ReplyDto> reply;

    public AnswerDetailsDto(Answer answer) {
        this.answerId = answer.getId();

    }

}
