package com.educhoice.motherchoice.models.persistent;

import org.junit.Test;

import static org.junit.Assert.*;

public class GradesTest {

    @Test
    public void 학년중분류검색_잘되는지() {
        assertTrue(Grades.ELEMENTARY == Grades.findBySpecifiedGrades(Grades.SpecifiedGrades.ELEMENTARY_3));
    }

}