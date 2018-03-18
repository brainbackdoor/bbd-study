package com.educhoice.motherchoice.valueobject.models;

import com.educhoice.motherchoice.models.persistent.Grades;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.educhoice.motherchoice.valueobject.models.query.QueryStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class AcademyQueryDtoTest {

    private final String TESTABLE_JSON_VALUE = "{\n" +
            "  \"queryStore\": {\n" +
            "    \"time\": {\n" +
            "      \"day\": [\n" +
            "        \"월\", \"수\", \"금\"\n" +

            "      ],\n" +
            "      \"startTime\": \"18:00\",\n" +
            "      \"endTime\": \"21:00\"\n" +
            "    },\n" +
            "    \"location\": {\n" +
            "      \"address\": \"대한민국 서울특별시 강남구 대치동\",\n" +
            "      \"latitude\": 37.4994914,\n" +
            "      \"longitude\": 127.0610527\n" +
            "    },\n" +
            "    \"grade\": \"중등2\",\n" +
            "    \"subject\": \"영어\",\n" +
            "    \"carAvailable\": true\n" +
            "  }\n" +
            "}";

    private static final Logger log = LoggerFactory.getLogger(AcademyQueryDtoTest.class);

    private ObjectMapper mapper;
    private AcademyQueryDto dto;

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();
        this.dto = AcademyQueryDto.builder()
                .carAvailable(false)
                .grade(Grades.SpecifiedGrades.ELEMENTARY_3)
                .address("경기도 김포시 풍무동")
                .subject("영어")
                .build();
    }

    @Test
    public void JSON파싱_잘먹는지() {

        AcademyQueryDto dto = null;

        try {
            dto = mapper.readValue(TESTABLE_JSON_VALUE, QueryStore.class).getQueryStore();
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        log.info(dto.toString());
        dto.getTime().generateDateTimefromDto().stream().forEach(System.out::println);

        assertTrue(dto.getGrade() == Grades.SpecifiedGrades.MIDDLE_2);

    }

    @Test
    public void JSON변환() throws Exception{
        log.debug(this.mapper.writeValueAsString(this.dto));
    }

}