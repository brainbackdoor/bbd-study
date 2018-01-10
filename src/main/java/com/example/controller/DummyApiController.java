package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.DummyData;
import com.example.model.Dummy;
import com.example.model.DummyAcademyAddress;
import com.example.model.DummyNickName;
import com.example.model.SearchAcademy;
import com.example.model.SearchAddress;
import com.example.model.SearchHashTag;
import com.example.model.SearchNickName;
import com.example.repository.SearchAcademyRepository;
import com.example.repository.SearchAddressRepository;
import com.example.repository.SearchHashTagRepository;
import com.example.repository.SearchNickNameRepository;

@RequestMapping("/api/exp")
@RestController
public class DummyApiController {

	
	private static final Logger log = LoggerFactory.getLogger(DummyApiController.class);

	
	@Autowired
	SearchAddressRepository searchAddressRepository;
	
	@Autowired
	SearchAcademyRepository searchAcademyRepository;
	
	@Autowired
	SearchNickNameRepository searchNickNameRepository;
	
	@Autowired
	SearchHashTagRepository searchHashTagRepository;

	/*
	 * Dummy
	 */
	@CrossOrigin
	@GetMapping("/dummy/editDetail/{academyId}")
	public Dummy returnDummyEditDetail(@PathVariable("academyId") long academyId) {
		return new DummyData().dummyEditDetail(academyId, "3관피큐브학원",
				new DummyAcademyAddress("서울 강남구 대치동 941-22 한나빌딩", 127.05975114071488, 37.498884549015784));
	}

	@CrossOrigin
	@ResponseBody
	@PostMapping("/dummy/searchAcademies")
	public List<Dummy> returnDummySearchAcademies(@RequestBody String jsonValues) {
		List<Dummy> dummies = new ArrayList<>();
		dummies.add(new DummyData().dummySearchAcademy(1, "3관피큐브학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05975114071488,37.498884549015784)));
		dummies.add(new DummyData().dummySearchAcademy(2, "4관피큐브학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0574118750373,37.49218221925881)));
		dummies.add(new DummyData().dummySearchAcademy(3, "AwesomeEnglish학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05475642710299,37.49785621854038)));
		dummies.add(new DummyData().dummySearchAcademy(4, "A플러스영수전문학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05516770344329,37.497369483749004)));
		dummies.add(new DummyData().dummySearchAcademy(5, "B.K.영어전문학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05506865389927,37.49800383844723)));
		dummies.add(new DummyData().dummySearchAcademy(6, "BMA(비엠에이)유명제5학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0613974936218,37.49863142310217)));
		dummies.add(new DummyData().dummySearchAcademy(7, "BMA유명학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0616957911155,37.50382106111076)));
		dummies.add(new DummyData().dummySearchAcademy(8, "CMS중계중등영재2관학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05795484050373,37.49243063213406)));
		dummies.add(new DummyData().dummySearchAcademy(9, "CNI수학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06395977094611,37.50094745109277)));
		dummies.add(new DummyData().dummySearchAcademy(10, "D.S브릿지보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05523500533056,37.49662341893901)));
		dummies.add(new DummyData().dummySearchAcademy(11, "NOV보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0619178573434,37.49334764879847)));
		dummies.add(new DummyData().dummySearchAcademy(12, "PI수학보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06232066899774,37.49364657131347)));
		dummies.add(new DummyData().dummySearchAcademy(13, "더프라임수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05856429912842,37.50252520716372)));
		dummies.add(new DummyData().dummySearchAcademy(14, "동신보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05315287976153,37.501071740699864)));
		dummies.add(new DummyData().dummySearchAcademy(15, "두드림독학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05892117572154,37.50189072171177)));
		dummies.add(new DummyData().dummySearchAcademy(16, "서울엘리트학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05840977341074,37.50160265384618)));
		dummies.add(new DummyData().dummySearchAcademy(17, "서울청목학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06005482487987,37.50529234740037)));
		dummies.add(new DummyData().dummySearchAcademy(18, "서일학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06140263093964,37.499381057542614)));
		dummies.add(new DummyData().dummySearchAcademy(19, "서초아카데미학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0608193245646,37.49966607720655)));
		dummies.add(new DummyData().dummySearchAcademy(20, "서초우리들학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05797241294333,37.497609610406975)));
		dummies.add(new DummyData().dummySearchAcademy(21, "서현수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05948132214644,37.500892126583075)));
		dummies.add(new DummyData().dummySearchAcademy(22, "선광보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0577062765306,37.492701055950114)));
		dummies.add(new DummyData().dummySearchAcademy(23, "선수학보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0577062765306,37.492701055950114)));
		dummies.add(new DummyData().dummySearchAcademy(24, "선진보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0611176006106,37.49932354096015)));
		dummies.add(new DummyData().dummySearchAcademy(25, "엘이아이학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0614189304168,37.49718980186307)));
		dummies.add(new DummyData().dummySearchAcademy(26, "엠로고스학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05815795776442,37.49771763997253)));
		dummies.add(new DummyData().dummySearchAcademy(27, "잉글리시아이갈현학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06373690257615,37.49952398215807)));
		dummies.add(new DummyData().dummySearchAcademy(28, "장현진학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.058611375457,37.49909054940475)));
		dummies.add(new DummyData().dummySearchAcademy(29, "적중수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05874230649262,37.498769726176576)));
		dummies.add(new DummyData().dummySearchAcademy(30, "프라임에듀학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0574118750373,37.49218221925881)));
		return dummies;
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("/dummy/searchCourses")
	public List<Dummy> returnDummySearchCoureses(@RequestBody String jsonValues) {
		List<Dummy> dummies = new ArrayList<>();
		dummies.add(new DummyData().dummySearchCourses(1, "3관피큐브학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05975114071488,37.498884549015784)));
		dummies.add(new DummyData().dummySearchCourses(2, "4관피큐브학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0574118750373,37.49218221925881)));
		dummies.add(new DummyData().dummySearchCourses(3, "AwesomeEnglish학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05475642710299,37.49785621854038)));
		dummies.add(new DummyData().dummySearchCourses(4, "A플러스영수전문학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05516770344329,37.497369483749004)));
		dummies.add(new DummyData().dummySearchCourses(5, "B.K.영어전문학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05506865389927,37.49800383844723)));
		dummies.add(new DummyData().dummySearchCourses(6, "BMA(비엠에이)유명제5학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0613974936218,37.49863142310217)));
		dummies.add(new DummyData().dummySearchCourses(7, "BMA유명학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0616957911155,37.50382106111076)));
		dummies.add(new DummyData().dummySearchCourses(8, "CMS중계중등영재2관학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05795484050373,37.49243063213406)));
		dummies.add(new DummyData().dummySearchCourses(9, "CNI수학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06395977094611,37.50094745109277)));
		dummies.add(new DummyData().dummySearchCourses(10, "D.S브릿지보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05523500533056,37.49662341893901)));
		dummies.add(new DummyData().dummySearchCourses(11, "NOV보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0619178573434,37.49334764879847)));
		dummies.add(new DummyData().dummySearchCourses(12, "PI수학보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06232066899774,37.49364657131347)));
		dummies.add(new DummyData().dummySearchCourses(13, "더프라임수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05856429912842,37.50252520716372)));
		dummies.add(new DummyData().dummySearchCourses(14, "동신보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05315287976153,37.501071740699864)));
		dummies.add(new DummyData().dummySearchCourses(15, "두드림독학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05892117572154,37.50189072171177)));
		dummies.add(new DummyData().dummySearchCourses(16, "서울엘리트학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05840977341074,37.50160265384618)));
		dummies.add(new DummyData().dummySearchCourses(17, "서울청목학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06005482487987,37.50529234740037)));
		dummies.add(new DummyData().dummySearchCourses(18, "서일학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06140263093964,37.499381057542614)));
		dummies.add(new DummyData().dummySearchCourses(19, "서초아카데미학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0608193245646,37.49966607720655)));
		dummies.add(new DummyData().dummySearchCourses(20, "서초우리들학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05797241294333,37.497609610406975)));
		dummies.add(new DummyData().dummySearchCourses(21, "서현수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05948132214644,37.500892126583075)));
		dummies.add(new DummyData().dummySearchCourses(22, "선광보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0577062765306,37.492701055950114)));
		dummies.add(new DummyData().dummySearchCourses(23, "선수학보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0577062765306,37.492701055950114)));
		dummies.add(new DummyData().dummySearchCourses(24, "선진보습학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0611176006106,37.49932354096015)));
		dummies.add(new DummyData().dummySearchCourses(25, "엘이아이학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0614189304168,37.49718980186307)));
		dummies.add(new DummyData().dummySearchCourses(26, "엠로고스학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05815795776442,37.49771763997253)));
		dummies.add(new DummyData().dummySearchCourses(27, "잉글리시아이갈현학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.06373690257615,37.49952398215807)));
		dummies.add(new DummyData().dummySearchCourses(28, "장현진학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.058611375457,37.49909054940475)));
		dummies.add(new DummyData().dummySearchCourses(29, "적중수학학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.05874230649262,37.498769726176576)));
		dummies.add(new DummyData().dummySearchCourses(30, "프라임에듀학원", new DummyAcademyAddress("서울특별시", "강남구","대치동",127.0574118750373,37.49218221925881)));
		return dummies;
	}
	
	@CrossOrigin
	@GetMapping("/dummy/searchAcademy/images/{academyId}")
	public Dummy returnDummySearchAcdemyImages(@PathVariable("academyId") long academyId) {
		return new DummyData().dummySearchAcdemyImages(academyId);
	}
	
	@CrossOrigin
	@GetMapping("/dummy/detail/{academyId}")
	public Dummy returnDummyDetailAcademy(@PathVariable("academyId") long academyId) {
		return new DummyData().dummyDetailAcademy(academyId, "3관피큐브학원",
				new DummyAcademyAddress("서울특별시", "강남구","대치동", 127.05975114071488, 37.498884549015784));
	}	
	/*
	 * Academy Name Search
	 */
	@CrossOrigin
	@GetMapping("/dummy/academy/{value}")
	public List<SearchAcademy> findByAcademyContaining(@PathVariable("value") String value) {
		return searchAcademyRepository.findByValueContaining(value);
	}	
	
	/*
	 * Nick Name Search
	 */
	
	@CrossOrigin
	@GetMapping("/dummy/nickname/{value}")
	public DummyNickName findByNickName(@PathVariable("value") String value) {
		return new DummyNickName(searchNickNameRepository.findByValueContaining(value),searchNickNameRepository.findByValueContaining(value).isEmpty());
		
	}		
	@CrossOrigin
	@GetMapping("/dummy/nickname/confirm/{value}")
	public List<SearchNickName> findByNickNameContaining(@PathVariable("value") String value) {
		return searchNickNameRepository.findByValueContaining(value);
	}
	/*
	 * Hash Tag Search
	 */	
	
	@CrossOrigin
	@GetMapping("/dummy/hashTag/{value}")
	public List<SearchHashTag> findByHashTagContaining(@PathVariable("value") String value) {
		return searchHashTagRepository.findByValueContaining(value);
	}
	/*
	 * End
	 */

	@CrossOrigin
	@GetMapping("/dummy/address/all")
	public List<SearchAddress> returnDummyAll() {
		return searchAddressRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/dummy/address/{key}")
	public SearchAddress returnDummy(@PathVariable("key") long key) {
		return searchAddressRepository.findByKey(key);
	}

	@CrossOrigin
	@GetMapping("/dummy/address/{value}")
	public List<SearchAddress> findByAddressContaining(@PathVariable("value") String value) {
		return searchAddressRepository.findByValueContaining(value);
	}

}
