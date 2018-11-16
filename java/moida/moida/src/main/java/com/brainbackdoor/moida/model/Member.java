package com.brainbackdoor.moida.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String name;
    private String blogLink;
    private String fbLink;
}
