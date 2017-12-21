package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import lombok.Setter;
import com.educhoice.motherchoice.models.persistent.Grades.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "datetimeno")
    private List<DateTime> dateTime;

    private SpecifiedGrades grades;
    private String title;

    //TODO add business logics for Course modification / courses open time calculation.
}
