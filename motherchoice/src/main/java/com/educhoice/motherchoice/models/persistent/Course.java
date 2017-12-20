package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {

    private List<DateTime> dateTime;
    private List<Grades> grades;

    //TODO add business logics for Course modification / courses open time calculation.
}
