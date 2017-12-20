package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
public class Academy {

    private List<Course> courses;
    private Address address;

    private boolean carAvailable;


}
