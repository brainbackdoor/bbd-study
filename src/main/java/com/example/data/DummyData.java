package com.example.data;

import java.util.ArrayList;
import java.util.List;

import com.example.model.DateTime;
import com.example.model.Dummy;
import com.example.model.DummyAcademyAddress;
import com.example.model.DummyAcademyResource;
import com.example.model.DummyCorporateAccount;
import com.example.model.DummyCourses;
import com.example.model.DummyEvent;
import com.example.model.DummyGrades;
import com.example.model.DummyHashTag;
import com.example.model.DummyImage;
import com.example.model.DummySpecialCourses;

public class DummyData {
	public Dummy dummyEditDetail(long id, String academyName, DummyAcademyAddress address) {
		Dummy dummy = new Dummy();
		dummy.setAccountId(id);
		dummy.setAcademyName(academyName);

		List<DummyImage> images = new ArrayList<>();
		images.add(new DummyImage((long)1, 0,"https://cdn.aws.com/imgs/a"));
		images.add(new DummyImage((long)2, 1,"https://cdn.aws.com/imgs/b"));
		images.add(new DummyImage((long)3, 1,"https://cdn.aws.com/imgs/c"));
		dummy.setImages(images);

		dummy.setAddress(address);
		
		dummy.setCorporateAccount(new DummyCorporateAccount(true, true, "02-2644-5096"));
		
		dummy.setCarAvailable(true);
		
		List<DummyHashTag> tags = new ArrayList<>();
		tags.add(new DummyHashTag((long) 1, "최상위권"));
		tags.add(new DummyHashTag((long) 2, "선행학습"));
		tags.add(new DummyHashTag((long) 3, "맞춤형관리"));
		tags.add(new DummyHashTag((long) 4, "자기주도학습"));
		tags.add(new DummyHashTag((long) 5, "소수정예"));
		dummy.setHashTags(tags);
		
		dummy.setInquiryResponseRate((double) 50);

		String introduction = "보이는 것은 거친 모래뿐일 것이다";
		dummy.setIntroduction(introduction);

		List<DummyCourses> courses = new ArrayList<>();
		List<DateTime> dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "화"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "수"));
		dayOfWeek.add(new DateTime("20:00", "22:00", "목"));
		dayOfWeek.add(new DateTime("20:00", "22:00", "금"));
		
		courses.add(new DummyCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek));
		dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		courses.add(new DummyCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek));
		courses.add(new DummyCourses(3, "과학", "물리", "무리무리-고3", "고등3", (long) 400000, dayOfWeek));
		dummy.setCourses(courses);

		List<DummySpecialCourses> specialCourses = new ArrayList<>();
		specialCourses.add(new DummySpecialCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		specialCourses.add(new DummySpecialCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		dummy.setSpecialCourses(specialCourses);

		List<DummyEvent> events = new ArrayList<>();
		events.add(new DummyEvent((long) 1,"특목고(1부) / 대입(2부) 입시설명회 개최", "인생에 가치를 주는 원질이 되는 것이다", "0000.00.00", true));
		events.add(new DummyEvent((long) 2,"2018년도 예비중1 / 예비고1 신규 개강", "남는 것은 영락과 부패 뿐이다", "0000.00.00", false));
		dummy.setEvents(events);

		List<DummyAcademyResource> academyResources = new ArrayList<>();
		academyResources.add(new DummyAcademyResource("홍보전단지.PNG", "https://cdn.aws.com/a"));
		academyResources.add(new DummyAcademyResource("홍보홍보.PNG", "https://cdn.aws.com/b"));
		dummy.setAcademyResources(academyResources);
		return dummy;
	}
	public Dummy dummySearchAcademy(long id, String academyName, DummyAcademyAddress address) {
		Dummy dummy = new Dummy();
		dummy.setAccountId(id);
		dummy.setAcademyName(academyName);

		List<DummyImage> images = new ArrayList<>();
		images.add(new DummyImage((long)1, 0,"https://cdn.aws.com/imgs/a"));
		images.add(new DummyImage((long)2, 1,"https://cdn.aws.com/imgs/b"));
		images.add(new DummyImage((long)3, 2,"https://cdn.aws.com/imgs/c"));
		dummy.setImages(images);

		dummy.setAddress(address);
		
		dummy.setCorporateAccount(new DummyCorporateAccount("02-2644-5096"));
		
		dummy.setCarAvailable(true);
		dummy.setInquiryResponseRate((double) 50);

		List<DummyGrades> grades = new ArrayList<>();
		grades.add(new DummyGrades("유아", 0));
		grades.add(new DummyGrades("초등", 100000));
		grades.add(new DummyGrades("중등", 100000));
		grades.add(new DummyGrades("고등", 100000));
		dummy.setGrades(grades);

		List<String> subjects = new ArrayList<>();
		subjects.add("국어");
		subjects.add("영어");
		subjects.add("수학");
		subjects.add("사회");
		subjects.add("과학");
		dummy.setSubjects(subjects);

		List<DummyHashTag> tags = new ArrayList<>();
		tags.add(new DummyHashTag((long) 1, "최상위권"));
		tags.add(new DummyHashTag((long) 2, "선행학습"));
		tags.add(new DummyHashTag((long) 3, "맞춤형관리"));
		tags.add(new DummyHashTag((long) 4, "자기주도학습"));
		tags.add(new DummyHashTag((long) 5, "소수정예"));
		dummy.setHashTags(tags);

		return dummy;
	}
	
	public Dummy dummySearchCourses(long id, String academyName, DummyAcademyAddress address) {
		Dummy dummy = new Dummy();
		dummy.setAccountId(id);
		dummy.setAcademyName(academyName);

		List<DummyImage> images = new ArrayList<>();
		images.add(new DummyImage((long)1, 0,"https://cdn.aws.com/imgs/a"));
		images.add(new DummyImage((long)2, 1,"https://cdn.aws.com/imgs/b"));
		images.add(new DummyImage((long)3, 2,"https://cdn.aws.com/imgs/c"));
		dummy.setImages(images);

		dummy.setAddress(address);
		
		dummy.setCorporateAccount(new DummyCorporateAccount("02-2644-5096"));
		
		dummy.setCarAvailable(true);
		dummy.setInquiryResponseRate((double) 50);

		List<DummyGrades> grades = new ArrayList<>();
		grades.add(new DummyGrades("유아", 0));
		grades.add(new DummyGrades("초등", 100000));
		grades.add(new DummyGrades("중등", 100000));
		grades.add(new DummyGrades("고등", 100000));
		dummy.setGrades(grades);

		List<String> subjects = new ArrayList<>();
		subjects.add("국어");
		subjects.add("영어");
		subjects.add("수학");
		subjects.add("사회");
		subjects.add("과학");
		dummy.setSubjects(subjects);

		List<DummyCourses> courses = new ArrayList<>();
		List<DateTime> dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "화"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "수"));
		dayOfWeek.add(new DateTime("20:00", "22:00", "목"));
		dayOfWeek.add(new DateTime("20:00", "22:00", "금"));
		
		courses.add(new DummyCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek));
		dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		courses.add(new DummyCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek));
		courses.add(new DummyCourses(3, "과학", "물리", "무리무리-고3", "고등3", (long) 400000, dayOfWeek));
		dummy.setCourses(courses);

		List<DummySpecialCourses> specialCourses = new ArrayList<>();
		specialCourses.add(new DummySpecialCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		specialCourses.add(new DummySpecialCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		dummy.setSpecialCourses(specialCourses);

		List<DummyHashTag> tags = new ArrayList<>();
		tags.add(new DummyHashTag((long) 1, "최상위권"));
		tags.add(new DummyHashTag((long) 2, "선행학습"));
		tags.add(new DummyHashTag((long) 3, "맞춤형관리"));
		tags.add(new DummyHashTag((long) 4, "자기주도학습"));
		tags.add(new DummyHashTag((long) 5, "소수정예"));
		dummy.setHashTags(tags);

		return dummy;
	}
	
	
	public Dummy dummySearchAcdemyImages(long id) {
		Dummy dummy = new Dummy();
		dummy.setAccountId(id);
		
		List<DummyImage> images = new ArrayList<>();
		images.add(new DummyImage((long)1, 0,"https://cdn.aws.com/imgs/a"));
		images.add(new DummyImage((long)2, 2,"https://cdn.aws.com/imgs/b"));
		images.add(new DummyImage((long)3, 2,"https://cdn.aws.com/imgs/c"));
		images.add(new DummyImage((long)4, 2,"https://cdn.aws.com/imgs/d"));
		images.add(new DummyImage((long)5, 2,"https://cdn.aws.com/imgs/e"));
		images.add(new DummyImage((long)6, 2,"https://cdn.aws.com/imgs/f"));
		images.add(new DummyImage((long)7, 2,"https://cdn.aws.com/imgs/g"));
		images.add(new DummyImage((long)8, 2,"https://cdn.aws.com/imgs/h"));
		dummy.setImages(images);
		
		return dummy;
	}
	
	public Dummy dummyDetailAcademy(long id, String academyName, DummyAcademyAddress address) {
		Dummy dummy = new Dummy();
		dummy.setAccountId(id);
		dummy.setAcademyName(academyName);

		List<DummyImage> images = new ArrayList<>();
		images.add(new DummyImage((long)1, 0,"https://cdn.aws.com/imgs/a"));
		images.add(new DummyImage((long)2, 1,"https://cdn.aws.com/imgs/b"));
		images.add(new DummyImage((long)3, 1,"https://cdn.aws.com/imgs/c"));
		images.add(new DummyImage((long)3, 1,"https://cdn.aws.com/imgs/d"));
		images.add(new DummyImage((long)3, 1,"https://cdn.aws.com/imgs/e"));
		dummy.setImages(images);

		dummy.setAddress(address);
		
		dummy.setCorporateAccount(new DummyCorporateAccount("02-2644-5096"));

		List<String> subjects = new ArrayList<>();
		subjects.add("국어");
		subjects.add("영어");
		subjects.add("수학");
		subjects.add("사회");
		subjects.add("과학");
		dummy.setSubjects(subjects);
		
		dummy.setCarAvailable(true);
		dummy.setInquiryResponseRate((double) 50);
		
		List<DummyGrades> grades = new ArrayList<>();
		grades.add(new DummyGrades("유아", 0));
		grades.add(new DummyGrades("초등", 100000));
		grades.add(new DummyGrades("중등", 100000));
		grades.add(new DummyGrades("고등", 100000));
		dummy.setGrades(grades);

		String introduction = "보이는 것은 거친 모래뿐일 것이다";
		dummy.setIntroduction(introduction);
		
		List<DummyCourses> courses = new ArrayList<>();
		List<DateTime> dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "화"));
		dayOfWeek.add(new DateTime("19:00", "21:00", "수"));
		
		courses.add(new DummyCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek));
		dayOfWeek = new ArrayList<>();
		dayOfWeek.add(new DateTime("19:00", "21:00", "월"));
		courses.add(new DummyCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek));
		courses.add(new DummyCourses(3, "과학", "물리", "무리무리-고3", "고등3", (long) 400000, dayOfWeek));
		dummy.setCourses(courses);

		List<DummySpecialCourses> specialCourses = new ArrayList<>();
		specialCourses.add(new DummySpecialCourses(1, "사회", "한국지리", "한국지리(사탐)-고3", "고등3", (long) 200000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		specialCourses.add(new DummySpecialCourses(2, "영어", "영어인증시험", "토익완성 -고3", "고등2", (long) 300000, dayOfWeek,
				"2017.12.01 ~ 2017.12.31"));
		dummy.setSpecialCourses(specialCourses);
		
		List<DummyEvent> events = new ArrayList<>();
		events.add(new DummyEvent((long) 1,"특목고(1부) / 대입(2부) 입시설명회 개최", "인생에 가치를 주는 원질이 되는 것이다", "0000.00.00", true));
		events.add(new DummyEvent((long) 2,"2018년도 예비중1 / 예비고1 신규 개강", "남는 것은 영락과 부패 뿐이다", "0000.00.00", false));
		dummy.setEvents(events);
		
		List<DummyAcademyResource> academyResources = new ArrayList<>();
		academyResources.add(new DummyAcademyResource("홍보전단지.PNG", "https://cdn.aws.com/a"));
		academyResources.add(new DummyAcademyResource("홍보홍보.PNG", "https://cdn.aws.com/b"));
		dummy.setAcademyResources(academyResources);

		return dummy;
	}
}
