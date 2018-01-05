package com.example;

import com.example.model.Grades;
import com.example.model.valueobject.AcademyQueryDto;
import com.example.model.valueobject.QueryStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class AcademyQueryDtoTest {

	private final String TESTABLE_JSON_VALUE = "{\n" + "  \"queryStore\": {\n" + "    \"time\": {\n"
			+ "      \"day\": [\n" + "        \"월\"\n" +

			"      ],\n" + "      \"startTime\": \"18:00\",\n" + "      \"endTime\": \"21:00\"\n" + "    },\n"
			+ "    \"location\": {\n" + "      \"address\": \"대한민국 서울특별시 강남구 대치동\",\n"
			+ "      \"latitude\": 37.4994914,\n" + "      \"longitude\": 127.0610527\n" + "    },\n"
			+ "    \"grade\": \"중등\",\n" + "    \"subject\": \"영어\",\n" + "    \"carAvailable\": true\n" + "  }\n"
			+ "}";

	private static final Logger log = LoggerFactory.getLogger(AcademyQueryDtoTest.class);

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		this.mapper = new ObjectMapper();
	}

	@Test
	public void JSON파싱_잘먹는지() {

		AcademyQueryDto dto = null;

		try {
			dto = mapper.readValue(TESTABLE_JSON_VALUE, QueryStore.class).getQueryStore();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.info(dto.toString());
		dto.getTime().generateDateTimefromDto().stream().forEach(System.out::println);

		assertTrue(dto.getGrade() == Grades.SpecifiedGrades.MIDDLE_ALL);

	}

	@Test
	public void JSON에서_동이름추출() throws Exception {
		AcademyQueryDto dto = null;
		try {
			dto = mapper.readValue(TESTABLE_JSON_VALUE, QueryStore.class).getQueryStore();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info(dto.toString());

		assertEquals("대치동", dto.getLocation().extractDongName());
	}
}