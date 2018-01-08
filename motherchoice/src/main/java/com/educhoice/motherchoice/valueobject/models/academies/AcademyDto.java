package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.HashTag;
import com.educhoice.motherchoice.models.persistent.Image;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AcademyDto {

    private long id;
    private String academyName;
    private List<Image> images;
    private AcademyAddress address;
    private boolean carAvailable;
    private double inquiryResponseRate;
    private List<GradeDto> grades;
    private List<String> subjects;
    private List<CourseDto> courses;
    private List<SpecialCourseDto> specialCourses;
    private List<HashTag> hashTags;
    private CorporateAccount corporateAccount;

}
