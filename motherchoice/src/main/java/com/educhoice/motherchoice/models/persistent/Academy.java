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
    private List<Course> courses = Lists.newArrayList();

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

    @Embedded
    private AcademyAddress address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "academyId")
    private List<Event> events;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
        this.carAvailable = dto.isCarAvailable();
        this.introduction = dto.getIntroduction();
        this.tags = dto.getHashTags();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Academy academy = (Academy) o;
        return carAvailable == academy.carAvailable &&
                Objects.equal(courses, academy.courses) &&
                Objects.equal(academyName, academy.academyName) &&
                Objects.equal(address, academy.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courses, academyName, address, carAvailable);
    }

    @Override
    public String toString() {
        return "Academy{" +
                "  courses=" + courses +
                ", academyName='" + academyName + '\'' +
                ", address=" + address +
                ", carAvailable=" + carAvailable +
                '}';
    }
}
