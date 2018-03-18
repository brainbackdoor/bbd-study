package com.educhoice.motherchoice.valueobject.models.academies;

import com.google.common.base.Objects;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDto {

    private String name;
    private long tuitionAvg;

    //TODO Write model-based constructor.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeDto gradeDto = (GradeDto) o;
        return tuitionAvg == gradeDto.tuitionAvg &&
                Objects.equal(name, gradeDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, tuitionAvg);
    }
}
