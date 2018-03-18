package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dummy {
    private long accountId;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private String academyName;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyImage> images;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private DummyAcademyAddress address;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private boolean carAvailable;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private double inquiryResponseRate;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyGrades> grades;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<String> subjects;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private String introduction;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyCourses> courses;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummySpecialCourses> specialCourses;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyEvent> events;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyAcademyResource> academyResources;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private List<DummyHashTag> hashTags;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) 
    private DummyCorporateAccount corporateAccount;
}
