package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.*;
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
    private String introduction;
    private List<CourseDto> courses;
    private List<SpecialCourseDto> specialCourses;
    private List<Event> events;
    private List<HashTag> hashTags;
    private CorporateAccount corporateAccount;
    private String message;

    public static AcademyDto generateAcademyDto(Academy academy) {

        if (academy instanceof EmptyAcademy) {
            EmptyAcademy emptyAcademy = (EmptyAcademy)academy;
            return AcademyDto.builder()
                    .id(0L)
                    .message(emptyAcademy.getMessage())
                    .build();
        }

        return AcademyDto.builder()
                .id(academy.getAcademyId())
                .academyName(academy.getAcademyName())
                .address(academy.getAddress())
                .carAvailable(academy.isCarAvailable())
                .grades(academy.getGradeAvgDtos())
                .subjects(academy.getSubjectsSummary())
                .introduction(academy.getIntroduction())
                .courses(CourseDto.generateCourseDtoFromAcademy(academy))
                .specialCourses(SpecialCourseDto.generateDto(academy))
                .events(academy.getEvents())
                .hashTags(academy.getTags())
                .corporateAccount(academy.getCorporateAccount())
                .build();
    }

}
