package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.utils.exceptions.NoGradeDefException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Grades {

    PRESCHOOL(Arrays.asList(SpecifiedGrades.PRESCHOOL_ALL), "유아"),
    ELEMENTARY(Arrays.asList(SpecifiedGrades.ELEMENTARY_ALL, SpecifiedGrades.ELEMENTARY_1, SpecifiedGrades.ELEMENTARY_2, SpecifiedGrades.ELEMENTARY_3, SpecifiedGrades.ELEMENTARY_4, SpecifiedGrades.ELEMENTARY_5, SpecifiedGrades.ELEMENTARY_6), "초"),
    MIDDLE(Arrays.asList(SpecifiedGrades.MIDDLE_ALL, SpecifiedGrades.MIDDLE_1, SpecifiedGrades.MIDDLE_2, SpecifiedGrades.MIDDLE_3), "중"),
    HIGH(Arrays.asList(SpecifiedGrades.HIGH_ALL, SpecifiedGrades.HIGH_1, SpecifiedGrades.HIGH_2, SpecifiedGrades.HIGH_3), "고");

    private List<SpecifiedGrades> specifiedGrades;
    private String symbol;

    Grades(List<SpecifiedGrades> specifiedGrades, String symbol) {
        this.specifiedGrades = specifiedGrades;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public static Grades findBySpecifiedGrades(SpecifiedGrades grade) {
        return Arrays.stream(Grades.values()).filter(grades -> grades.isContainingGrade(grade)).findAny().orElseThrow(() -> new NoGradeDefException("no matching grade found!"));
    }

    private boolean isContainingGrade(SpecifiedGrades grade) {
        return this.specifiedGrades.stream().anyMatch(g -> g == grade);
    }

    public enum SpecifiedGrades {
        PRESCHOOL_ALL(""),
        ELEMENTARY_ALL("초등"),
        ELEMENTARY_1("초1"),
        ELEMENTARY_2("초2"),
        ELEMENTARY_3("초3"),
        ELEMENTARY_4("초4"),
        ELEMENTARY_5("초5"),
        ELEMENTARY_6("초6"),
        MIDDLE_ALL("중등"),
        MIDDLE_1("중1"),
        MIDDLE_2("중2"),
        MIDDLE_3("중3"),
        HIGH_ALL("고등"),
        HIGH_1("고1"),
        HIGH_2("고2"),
        HIGH_3("고3");

        private String symbol;

        SpecifiedGrades(String symbol) {
            this.symbol = symbol;
        }

        @JsonValue
        public String getSymbol() {
            return this.symbol;
        }
    }

}
