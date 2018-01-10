package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DummyAcademyAddress {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	String sido;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	String sigungu;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	String dong;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	String address;
	
	
	double latitude;
	double longitude;

	public DummyAcademyAddress(String address, double latitude, double longitude) {
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public DummyAcademyAddress(String sido, String sigungu, String dong, double latitude, double longitude) {
		this.sido = sido;
		this.sigungu = sigungu;
		this.dong = dong;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
