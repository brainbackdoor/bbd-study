package com.example.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dummy {
	long academy_id;
	String academyName;
	DummyAddress address;
	boolean carAvailable;
	List<DummyCourses> courses;
	DummyTuition tuition;
	List<DummyHashTag> hashTag;
	int inquiryResponseRate;
	DummyCorporateAccount corporateAccount;
}
