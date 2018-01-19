package com.educhoice.motherchoice.models.persistent.geolocation;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Null;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademyAddress{

    private String sido;
    private String sigungu;

    @JsonIgnore
    private String address;

    @JsonIgnore
    private String roadAddress;

    @JsonIgnore
    private String jibunAddress;

    @JsonIgnore
    private String zonecode;

    private double latitude;
    private double longitude;


}
