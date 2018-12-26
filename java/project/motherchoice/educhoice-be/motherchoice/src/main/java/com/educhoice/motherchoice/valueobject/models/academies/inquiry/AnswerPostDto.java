package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnswerPostDto {
        private Long accountId;
        private Long questionId;
    private String answerContent;
}