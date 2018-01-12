package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionListDto {

    private long questionId;
    private String title;
    private String created;
    private int inquire;
    private int answer;
    private int additionalAnswer;

    //TODO Write model-based constructor.

}
