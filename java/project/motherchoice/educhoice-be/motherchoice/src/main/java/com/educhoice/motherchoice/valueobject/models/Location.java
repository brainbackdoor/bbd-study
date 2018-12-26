package com.educhoice.motherchoice.valueobject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {

    private String address;
    private double latitude;
    private double longitude;

}
