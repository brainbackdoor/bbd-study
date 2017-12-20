package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Academy {

    @OneToMany
    private List<Course> courses;

    private Address address;

    private boolean carAvailable;


}
