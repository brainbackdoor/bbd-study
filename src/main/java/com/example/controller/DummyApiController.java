package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.DummySearchAcademyAddress;
import com.example.model.DummyTuition;
import com.example.model.Grades;
import com.example.data.DummyData;
import com.example.model.Course;
import com.example.model.DateTime;
import com.example.model.Dummy;
import com.example.model.DummyAddress;
import com.example.model.DummyCorporateAccount;
import com.example.model.DummyCourses;
import com.example.model.DummyHashTag;
import com.example.model.DummySearchAcademy;
import com.example.model.SearchAddress;
import com.example.repository.DummySearchAcademyRepository;
import com.example.repository.SearchAddressRepository;
import com.google.common.collect.Lists;

@RequestMapping("/api/exp")
@RestController
public class DummyApiController {

	@Autowired
	SearchAddressRepository searchAddressRepository;
	
	@Autowired
	DummySearchAcademyRepository dummySearchAcademyRepository;

	/*
	 * Dummy
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping("/dummy/academy")
	public Dummy returnDummy(@RequestBody String jsonValues) {
		
		Dummy dummy = new Dummy();
		dummy.setAcademy_id((long) 1);
		dummy.setAcademyName("4관피큐브학원");
		dummy.setAddress(new DummyAddress((long) 16432,"서울", "강남구","대치동",37.49218221925881,127.0574118750373));
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
		dummy.setCorporateAccount(new DummyCorporateAccount("02-2644-5096"));

		return dummy;

	}
	@CrossOrigin
	@ResponseBody
	@PostMapping("/dummy/academies")
	public List<Dummy> returnDummyies(@RequestBody String jsonValues) {
		List<Dummy> dummies = new ArrayList<>();
		DummyData dummyData = new DummyData();
		dummies.add(dummyData.dummy1(1, "(상계분원)오메가수학학원", new DummyAddress((long) 16432,"서울", "강남구","대치동",37.498884549015784,127.05975114071488), new DummyCorporateAccount("02-2644-5096")));
		dummies.add(dummyData.dummy2(2, "(주)그루샘수학보습학원", new DummyAddress((long) 16384,"서울", "강남구","대치동",37.49218221925881,127.0574118750373), new DummyCorporateAccount("02-508-8441")));
		dummies.add(dummyData.dummy3(3, "(주)멘토르수학전문학원", new DummyAddress((long) 16386,"서울", "강남구","대치동",37.49785621854038,127.05475642710299), new DummyCorporateAccount("02-564-5002")));
		dummies.add(dummyData.dummy4(4, "(주)산에듀김영준국어논술전문별관학원", new DummyAddress((long) 16385,"서울", "강남구","대치동",37.497369483749004,127.05516770344329), new DummyCorporateAccount("02-501-0575")));
		dummies.add(dummyData.dummy2(5, "1230깊은생각학원", new DummyAddress((long) 16389,"서울", "강남구","대치동",37.49800383844723,127.05506865389927), new DummyCorporateAccount("02-2650-8720")));
		dummies.add(dummyData.dummy6(6, "윤선생우리집앞영어롯데주앤박학원", new DummyAddress((long) 16393,"서울", "강남구","대치동",37.49863142310217,127.0613974936218), new DummyCorporateAccount("02-374-8543")));
		dummies.add(dummyData.dummy3(7, "힘수학학원", new DummyAddress((long) 16434,"서울", "강남구","대치동",37.50382106111076,127.0616957911155), new DummyCorporateAccount("02-444-3554")));
		
		return dummies;
	}
	
	
	
	/*
	 * End
	 */
	@CrossOrigin
	@GetMapping("/dummies")
	public List<SearchAddress> returnDummyAll() {
		return searchAddressRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/dummy/{key}")
	public SearchAddress returnDummy(@PathVariable("key") long key) {
		return searchAddressRepository.findByKey(key);
	}

	@CrossOrigin
	@GetMapping("/dummy/contain/{address}")
	public List<SearchAddress> findByAddressContaining(@PathVariable("address") String address) {
		return searchAddressRepository.findByValueContaining(address);
	}
	
//	@GetMapping("/dummy/academy")
//	@CrossOrigin
//	public DummySearchAcademy returnDummyAcacdemy() {
//
//		return DummySearchAcademy.builder()
//				.address(Arrays.asList(
//						new DummySearchAcademyAddress("서울 노원구 상계동 455", 127.06697859544342,37.66444002512082)))
//				.carAvailable(true).academyName("(상계분원)오메가수학학원").courses(returnCourses()).build();
//
//	}
	
	private List<Course> returnCourses() {
		List<Course> courses = Lists.newArrayList();

		Course courseOne = new Course();
		courseOne.setDateTime(Arrays.asList(new DateTime("17:30", "19:30", "금")));
		courseOne.setTitle("TDD와 클린 코드 with Java");
		courseOne.setGrades(Grades.SpecifiedGrades.ELEMENTARY_6);
		courseOne.setCoursesClassification(Course.CoursesClassification.SpecifiedCoursesClassification.SCIENCE);
		courseOne.setCourseId(1L);

		courses.add(courseOne);

		return courses;

	}
}
