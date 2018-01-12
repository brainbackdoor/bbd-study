package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionPostDto {

    private List<Long> academies;
    private String questionTitle;
    private String questionContent;

    //TODO generate model entity from DTO.
}
