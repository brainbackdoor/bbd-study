package com.educhoice.motherchoice.models.persistent.geolocation;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademyAddress{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long addressId;

    @ManyToOne
    @JoinColumn(name = "academyId")
    @JsonIgnore
    private Academy academy;


    @ManyToOne
    @JoinColumn(name = "dongId")
    private Dong dong;

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

    @Override
    public String toString() {
        return "AcademyAddress{" +
                "id=" + addressId +
                ", sido='" + sido + '\'' +
                ", sigungu='" + sigungu + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", jibunAddress='" + jibunAddress + '\'' +
                ", zonecode='" + zonecode + '\'' +
                '}';
    }
}
