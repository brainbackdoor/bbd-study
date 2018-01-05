package com.example.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Dummy;
import com.example.model.DummyAddress;
import com.example.model.DummyCorporateAccount;
import com.example.model.DummyCourses;
import com.example.model.DummyHashTag;
import com.example.model.DummyTuition;

public class DummyData {
	public Dummy dummy1(long id, String academyName, DummyAddress address, DummyCorporateAccount corporate) {
		Dummy dummy = new Dummy();
		dummy.setAcademy_id(id);
		dummy.setAcademyName(academyName);
		dummy.setAddress(address);
		dummy.setCarAvailable(true);
		
		List<DummyCourses> courses = new ArrayList<>();
		courses.add(new DummyCourses(1,"국어","초등"));
		courses.add(new DummyCourses(2,"외국어","중등"));
		courses.add(new DummyCourses(3,"수학","고등"));
		courses.add(new DummyCourses(4,"사회","초등"));
		courses.add(new DummyCourses(5,"과학","중등"));
		
		dummy.setCourses(courses);
		dummy.setTuition(new DummyTuition((long) 1, 0, 200000, 250000, 450000));
		
		List<DummyHashTag> hashTag = new ArrayList<>();
		hashTag.add(new DummyHashTag((long) 1,"소수정예"));
		hashTag.add(new DummyHashTag((long) 2,"자기주도학습"));
		hashTag.add(new DummyHashTag((long) 3,"맞춤형관리"));
		hashTag.add(new DummyHashTag((long) 4,"선행학습"));
		hashTag.add(new DummyHashTag((long) 5,"최상위권"));
		dummy.setHashTag(hashTag);
		
		dummy.setInquiryResponseRate(50);
		dummy.setCorporateAccount(corporate);

		return dummy;
	}
	public Dummy dummy2(long id, String academyName, DummyAddress address, DummyCorporateAccount corporate) {
		Dummy dummy = new Dummy();
		dummy.setAcademy_id(id);
		dummy.setAcademyName(academyName);
		dummy.setAddress(address);
		dummy.setCarAvailable(true);
		
		List<DummyCourses> courses = new ArrayList<>();
		courses.add(new DummyCourses(1,"국어","초등"));
		courses.add(new DummyCourses(2,"수학","고등"));
		
		dummy.setCourses(courses);
		dummy.setTuition(new DummyTuition((long) 1, 0, 200000, 0, 450000));
		
		List<DummyHashTag> hashTag = new ArrayList<>();
		hashTag.add(new DummyHashTag((long) 1,"우왕"));
		hashTag.add(new DummyHashTag((long) 2,"아몰랑"));
		hashTag.add(new DummyHashTag((long) 3,"나한테왜이래"));
		hashTag.add(new DummyHashTag((long) 4,"다시생각해봐"));
		hashTag.add(new DummyHashTag((long) 5,"낄낄"));
		dummy.setHashTag(hashTag);
		
		dummy.setInquiryResponseRate(100);
		dummy.setCorporateAccount(corporate);

		return dummy;
	}
	public Dummy dummy3(long id, String academyName, DummyAddress address, DummyCorporateAccount corporate) {
		Dummy dummy = new Dummy();
		dummy.setAcademy_id(id);
		dummy.setAcademyName(academyName);
		dummy.setAddress(address);
		dummy.setCarAvailable(true);
		
		List<DummyCourses> courses = new ArrayList<>();
		courses.add(new DummyCourses(1,"수학","초등"));
		courses.add(new DummyCourses(2,"외국어","중등"));
		courses.add(new DummyCourses(3,"수학","중등"));
		courses.add(new DummyCourses(4,"사회","초등"));
		courses.add(new DummyCourses(5,"외국어","초등"));
		
		dummy.setCourses(courses);
		dummy.setTuition(new DummyTuition((long) 1, 0, 200000, 250000, 0));
		
		List<DummyHashTag> hashTag = new ArrayList<>();
		hashTag.add(new DummyHashTag((long) 1,"소수정예"));
		hashTag.add(new DummyHashTag((long) 2,"자기주도학습"));
		hashTag.add(new DummyHashTag((long) 3,"맞춤형관리"));
		hashTag.add(new DummyHashTag((long) 4,"선행학습"));
		hashTag.add(new DummyHashTag((long) 5,"최상위권"));
		dummy.setHashTag(hashTag);
		
		dummy.setInquiryResponseRate(50);
		dummy.setCorporateAccount(corporate);

		return dummy;
	}
	public Dummy dummy4(long id, String academyName, DummyAddress address, DummyCorporateAccount corporate) {
		Dummy dummy = new Dummy();
		dummy.setAcademy_id(id);
		dummy.setAcademyName(academyName);
		dummy.setAddress(address);
		dummy.setCarAvailable(true);
		
		List<DummyCourses> courses = new ArrayList<>();
		courses.add(new DummyCourses(1,"국어","고등"));

		
		dummy.setCourses(courses);
		dummy.setTuition(new DummyTuition((long) 1, 0, 0, 0, 450000));
		
		List<DummyHashTag> hashTag = new ArrayList<>();
		hashTag.add(new DummyHashTag((long) 1,"ㅇㅇㅇ"));
		hashTag.add(new DummyHashTag((long) 2,"kiki"));
		hashTag.add(new DummyHashTag((long) 3,"깔깔"));
		hashTag.add(new DummyHashTag((long) 4,"하하하"));
		hashTag.add(new DummyHashTag((long) 5,"호호호"));
		dummy.setHashTag(hashTag);
		
		dummy.setInquiryResponseRate(50);
		dummy.setCorporateAccount(corporate);

		return dummy;
	}
	public Dummy dummy6(long id, String academyName, DummyAddress address, DummyCorporateAccount corporate) {
		Dummy dummy = new Dummy();
		dummy.setAcademy_id(id);
		dummy.setAcademyName(academyName);
		dummy.setAddress(address);
		dummy.setCarAvailable(true);
		
		List<DummyCourses> courses = new ArrayList<>();
		courses.add(new DummyCourses(1,"국어","초등"));
		courses.add(new DummyCourses(2,"외국어","중등"));
		courses.add(new DummyCourses(3,"수학","고등"));
		courses.add(new DummyCourses(4,"사회","초등"));
		courses.add(new DummyCourses(5,"과학","초등"));
		
		dummy.setCourses(courses);
		dummy.setTuition(new DummyTuition((long) 1,0, 200000, 250000, 450000));
		
		List<DummyHashTag> hashTag = new ArrayList<>();
		hashTag.add(new DummyHashTag((long) 1,"코드스쿼드"));
		hashTag.add(new DummyHashTag((long) 2,"엄선"));
		hashTag.add(new DummyHashTag((long) 3,"알알"));
		hashTag.add(new DummyHashTag((long) 4,"페이커"));
		hashTag.add(new DummyHashTag((long) 5,"준준"));
		dummy.setHashTag(hashTag);
		
		dummy.setInquiryResponseRate(50);
		dummy.setCorporateAccount(corporate);

		return dummy;
	}
}
