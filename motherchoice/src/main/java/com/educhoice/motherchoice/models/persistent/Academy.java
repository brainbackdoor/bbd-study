package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long academyId;

    @OneToMany
    private List<Course> courses;

    @NotNull
    private String academyName;

    @OneToOne(mappedBy = "academy", cascade = CascadeType.ALL)
    private AcademyAddress address;

    private boolean carAvailable;


}
