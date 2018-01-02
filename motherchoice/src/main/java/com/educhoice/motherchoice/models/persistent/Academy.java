package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long academyId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses;

    @NotNull
    private String academyName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "address")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AcademyAddress> address;

    private boolean carAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CorporateAccount corporateAccount;

    public void addCourse(Course course) {
        if(this.courses == null) {
            this.courses = Lists.newArrayList();
        }
        this.courses.add(course);
    }

    public long calculateAvgTuition() {
        return (long) this.courses.stream().mapToLong(c -> c.getTuition()).average().orElse(0.0);
    }

    public long calculateElementaryAvgTuition() {
        return (long) this.courses.stream().filter(c -> Grades.findBySpecifiedGrades(c.getGrades()) == Grades.ELEMENTARY).mapToLong(elementary -> elementary.getTuition()).average().orElse(0.0);
    }

    public long calculateMiddleAvgTuition() {
        return (long) this.courses.stream().filter(c -> Grades.findBySpecifiedGrades(c.getGrades()) == Grades.MIDDLE).mapToLong(middle -> middle.getTuition()).average().orElse(0.0);
    }

    public long calculateHighAvgTuition() {
        return (long) this.courses.stream().filter(c -> Grades.findBySpecifiedGrades(c.getGrades()) == Grades.HIGH).mapToLong(high -> high.getTuition()).average().orElse(0.0);
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
