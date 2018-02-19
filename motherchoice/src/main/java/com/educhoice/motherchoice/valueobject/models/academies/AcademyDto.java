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

    private AcademyDto(Academy academy, double inquiryResponseRate) {
        if (academy instanceof EmptyAcademy) {
            this.message = ((EmptyAcademy) academy).getMessage();
            return;
        }

        this.id = academy.getAcademyId();
        this.academyName = academy.getAcademyName();
        if (academy.getImages() != null) {
            this.images = academy.getImages();
        }
        this.address = academy.getAddress();
        this.carAvailable = academy.isCarAvailable();
        this.inquiryResponseRate = inquiryResponseRate;
        if (academy.getGradeAvgDtos() != null) {
            this.grades = academy.getGradeAvgDtos();
        }
        this.subjects = academy.getSubjectsSummary();
        this.introduction = academy.getIntroduction();
        if (academy.getCourses() != null) {
            this.courses = CourseDto.generateCourseDtoFromAcademy(academy);
        }
        if (academy.getSpecialCourses() != null) {
            this.specialCourses = SpecialCourseDto.generateDto(academy);
        }
        if (academy.getEvents() != null) {
            this.events = academy.getEvents();
        }
        if (academy.getTags() != null) {
            this.hashTags = academy.getTags();
        }
        this.corporateAccount = academy.getCorporateAccount();

    }

    public static AcademyDto generateAcademyDto(Academy academy, double inquiryResponseRate) {
        return new AcademyDto(academy, inquiryResponseRate);
    }



}
