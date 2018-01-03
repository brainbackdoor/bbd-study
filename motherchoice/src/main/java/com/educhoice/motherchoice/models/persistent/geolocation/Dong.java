package com.educhoice.motherchoice.models.persistent.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dongId;

    private String dongName;
    private String juso;

    @OneToMany
    @JoinColumn(name = "dongId")
    @JsonIgnore
    private List<AcademyAddress> addressList;


}
