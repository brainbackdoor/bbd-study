package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class NewAcademyDtoTest {

    private NewAcademyDto dto;

    @Before
    public void setUp() {
        this.dto = NewAcademyDto.builder()
                .name("포비학원")
                .ownerName("박재성")
                .phoneNumber("07012345678")
                .build();
    }

    @Test
    public void DTO에서_학원생성_테스트() {
        Academy academy = this.dto.generateNewAcademy();

        assertThat(academy.getAcademyName(), is("포비학원"));
    }

}