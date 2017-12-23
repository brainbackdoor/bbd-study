package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.Academy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Wonjang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wonjangId;

    private String email;
    private String password;

    private String phoneNo;
    private String ownerName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wonjangid")
    List<Academy> academies;


}
