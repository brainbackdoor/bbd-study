package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    private String questionTitle;
    private String questionContent;
    private List<AnswerListDto> answer;

    //TODO Write model-based constructor.

}
