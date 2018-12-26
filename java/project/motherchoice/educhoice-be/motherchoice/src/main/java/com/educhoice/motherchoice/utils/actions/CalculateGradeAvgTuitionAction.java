package com.educhoice.motherchoice.utils.actions;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.valueobject.models.academies.GradeDto;

public interface CalculateGradeAvgTuitionAction {

    GradeDto generateGradeDto(Academy academy);
}
