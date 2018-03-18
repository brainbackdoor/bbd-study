package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.Course;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SpecialCourse extends Course {

    @Column(name = "SPECIAL_COURSE_DURATION")
    @JsonProperty(value = "specialCourseId")
    private String duration;

    public SpecialCourse(String duration) {
        this.duration = duration;
    }
}
