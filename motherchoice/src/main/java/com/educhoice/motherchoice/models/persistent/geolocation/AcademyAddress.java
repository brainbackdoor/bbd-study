package com.educhoice.motherchoice.models.persistent.geolocation;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AcademyAddress extends Address{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long academyId;

    @ManyToOne
    @JsonIgnore
    private Dong dong;

    private String sido;
    private String sigungu;


    public AcademyAddress(String address, String roadAddress, String jibunAddress, String zonecode, String sido, String sigungu) {
        super(address, roadAddress, jibunAddress, zonecode);
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
