package com.example.model.valueobject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location {

    private String address;
    private double latitude;
    private double longitude;
    
    public String extractDongName() {
    	String[] addressValues = address.split(" ");
    	return addressValues[addressValues.length-1]; 	
    }


}
