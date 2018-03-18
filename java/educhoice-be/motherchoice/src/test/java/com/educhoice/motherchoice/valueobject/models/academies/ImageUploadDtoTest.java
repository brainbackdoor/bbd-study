package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Image;
import com.educhoice.motherchoice.service.AcademyService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageUploadDtoTest {

    private static final Logger log = LoggerFactory.getLogger(ImageUploadDtoTest.class);

    private Academy academy;
    private List<Image> images = Lists.newArrayList();

    @Autowired
    private AcademyService academyService;

    @Before
    public void setUp() {
        this.academy = Academy.builder()
                .academyName("포비학원")
                .build();

        IntStream.range(1, 6).forEach(i -> {
            Image image = new Image();
            image.setUri("/" + i);
            images.add(image);
        });
    }

    @Test
    public void 이미지_잘들어가는지() {
        ImageUploadDto dto = new ImageUploadDto();
        dto.setImgs(this.images);
        dto.setAcademyId(1);

        academyService.saveAcademy(this.academy);
        academyService.saveImages(dto);

        log.debug(academyService.getAcademyById(1).getImages().toString());
    }

}