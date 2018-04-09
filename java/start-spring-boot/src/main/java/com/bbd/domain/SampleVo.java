package com.bbd.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude= {"val3"})
public class SampleVo {
	private String val1;
	private String val2;
	private String val3;
	
}
