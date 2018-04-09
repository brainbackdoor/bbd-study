package com.bbd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.domain.SampleVo;

@RestController
public class SampleController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	@GetMapping("/sample")
	public SampleVo makeSample() {
		SampleVo vo = new SampleVo();
		vo.setVal1("v1");
		vo.setVal2("v2");
		vo.setVal3("v3");
		System.out.println(vo);
		return vo;
	}
	
}
