package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long academyId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses;

    @NotNull
    private String academyName;

    @OneToOne(mappedBy = "academy", cascade = CascadeType.ALL)
    private AcademyAddress address;

    private boolean carAvailable;

    public void addCourse(Course course) {
        if(this.courses == null) {
            this.courses = Lists.newArrayList();
        }
        this.courses.add(course);
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
                ", courses=" + courses +
                ", academyName='" + academyName + '\'' +
                ", address=" + address +
                ", carAvailable=" + carAvailable +
                '}';
    }
}
