package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDto {

    private String title;
    private String content;
    private List<AnswerDto> answer;

}
