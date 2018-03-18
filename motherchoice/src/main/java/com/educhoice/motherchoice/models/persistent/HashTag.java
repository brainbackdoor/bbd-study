package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.valueobject.models.academies.HashTagDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Academy> academies;

    private String title;

    public void addAcademy(Academy academy) {
        if(this.academies == null) {
            academies = Lists.newArrayList();
        }

        this.academies.add(academy);
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
