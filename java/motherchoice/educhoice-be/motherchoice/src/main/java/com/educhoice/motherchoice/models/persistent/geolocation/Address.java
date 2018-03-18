package com.educhoice.motherchoice.models.persistent.geolocation;

import lombok.Getter;

@Getter
public class Address {

    private String address;
    private String roadAddress;
    private String jibunAddress;
    private String zonecode;

    private double longitude;
    private double latitude;

    public Address(String address, String roadAddress, String jibunAddress, String zonecode) {
        this.address = address;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.zonecode = zonecode;
    }
}
