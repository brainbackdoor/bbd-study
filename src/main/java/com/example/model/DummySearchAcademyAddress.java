package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DummySearchAcademyAddress{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    private long academyId;


    @ManyToOne
    @JoinColumn(name = "dong_id")
    @JsonIgnore
    private Dong dong;

    private String sido;
    private String sigungu;
    private String address;
    private String roadAddress;
    private String jibunAddress;
    private String zonecode;
    
    private double longitude;
    private double latitude;    

    public DummySearchAcademyAddress() {}

    public DummySearchAcademyAddress(String sido, String sigungu, String address, String roadAddress, String jibunAddress, String zonecode) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.address = address;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.zonecode = zonecode;
    }


    
    @Override
    public String toString() {
        return "AcademyAddress{" +
                "id=" + id +
                ", academyId=" + academyId +
                ", sido='" + sido + '\'' +
                ", sigungu='" + sigungu + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", jibunAddress='" + jibunAddress + '\'' +
                ", zonecode='" + zonecode + '\'' +
                '}';
    }
}
