package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DummyCorporateAccount {
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	boolean payment;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	boolean view;
	String phoneNo;
	
	public DummyCorporateAccount(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
