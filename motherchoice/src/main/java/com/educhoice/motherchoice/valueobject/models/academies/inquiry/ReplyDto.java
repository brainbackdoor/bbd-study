package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReplyDto {

    private String role;
    private String accountName;
    private String content;
}
