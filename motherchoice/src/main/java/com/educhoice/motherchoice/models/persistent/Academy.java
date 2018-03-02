package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import com.educhoice.motherchoice.valueobject.models.academies.GradeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long academyId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACADEMY_ID")
    private List<Course> courses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SpecialCourse> specialCourses;

    @NotNull
    private String academyName;

    private String ownerName;
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    private boolean carAvailable;
    private boolean certified;
    private boolean displayed;

    @Embedded
    private AcademyAddress address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACADEMY_ID")
    private List<Event> events;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACADEMY_ID")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "academy")
    @JsonIgnore
    private CorporateAccount corporateAccount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HashTag> tags;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    public void addCourse(Course course) {
        if(this.courses == null) {
            this.courses = Lists.newArrayList();
        }
        this.courses.add(course);
    }

    public void addHashTags(HashTag tag) {
        if(this.tags == null) {
            this.tags = Lists.newArrayList();
        }
        this.tags.add(tag);
    }

    public List<GradeDto> getGradeAvgDtos() {
        if(this.courses == null) {
            return Arrays.asList(new GradeDto());
        }
        return this.courses.stream().filter(java.util.Objects::nonNull).map(c -> Grades.findBySpecifiedGrades(c.getGrades()).generateGradeDto(this)).distinct().collect(Collectors.toList());
    }

    public List<String> getSubjectsSummary() {
        return this.courses.stream().map(c -> Course.CoursesClassification.findBySpecifiedCourses(c.getCoursesClassification()).getSymbol()).distinct().collect(Collectors.toList());
    }

    public void update(AcademyDto dto) {
        this.courses = dto.getCourses().stream().map(c -> c.getCourseEntity()).collect(Collectors.toList());
        this.academyName = dto.getAcademyName();
        this.carAvailable = dto.isCarAvailable();
        this.introduction = dto.getIntroduction();
        this.tags = dto.getHashTags();
        this.events = dto.getEvents();
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academyId=" + academyId +
                ", courses=" + courses +
                ", specialCourses=" + specialCourses +
                ", academyName='" + academyName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", introduction='" + introduction + '\'' +
                ", carAvailable=" + carAvailable +
                ", address=" + address +
                ", events=" + events +
                ", images=" + images +
                ", corporateAccount=" + corporateAccount +
                ", tags=" + tags +
                ", questions=" + questions +
                '}';
    }
}
