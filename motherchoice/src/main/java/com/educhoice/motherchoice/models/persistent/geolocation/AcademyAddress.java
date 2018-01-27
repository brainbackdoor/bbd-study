package com.educhoice.motherchoice.models.persistent.geolocation;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String roadAddress;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jibunAddress;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String zonecode;

    @Column(columnDefinition = "DECIMAL NULL")
    private double latitude;

    @Column(columnDefinition = "DECIMAL NULL")
    private double longitude;


}
