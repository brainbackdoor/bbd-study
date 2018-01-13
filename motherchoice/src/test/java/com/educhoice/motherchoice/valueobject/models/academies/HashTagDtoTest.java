package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.HashTag;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.service.HashTagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HashTagDtoTest {

    private HashTag tag;
    private Academy academy;
    private static final Logger log = LoggerFactory.getLogger(HashTagDtoTest.class);

    @Autowired
    private HashTagService hashTagService;

    @Autowired
    private AcademyService academyService;

    @Before
    public void setUp() {
        this.tag = new HashTag();
        this.tag.setTitle("클린코드");

        this.academy = Academy.builder()
                .academyName("포비학원")
                .build();

    }

    @Test
    public void JSON_출력_검사() {
        try {
            log.debug(new ObjectMapper().writeValueAsString(this.tag));
        } catch (JsonProcessingException e) {
            log.error("you've got an error! : {}" , e.getMessage());
        }
    }

    @Test
    public void 학원태그_추가_검사() {
        hashTagService.saveHashTag(this.tag);
        academyService.saveAcademy(this.academy);

        HashTagDto dto = new HashTagDto(1, 1, "클린코드");
        this.hashTagService.updateHashtag(dto);

        log.debug(this.hashTagService.findExistingHashTags("클린코").toString());
        assertNotNull(this.hashTagService.findExistingHashTags("클린코드"));
        assertEquals(this.hashTagService.findExistingHashTags("클린코드").get(0).getAcademies().get(0).getAcademyName(), "포비학원");
    }


}