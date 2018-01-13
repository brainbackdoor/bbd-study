package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.utils.actions.CalculateGradeAvgTuitionAction;
import com.educhoice.motherchoice.utils.exceptions.entity.NoGradeDefException;
import com.educhoice.motherchoice.valueobject.models.academies.GradeDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Grades implements CalculateGradeAvgTuitionAction{

    PRESCHOOL(Arrays.asList(SpecifiedGrades.PRESCHOOL_ALL), "유아") {
        @Override
        public GradeDto generateGradeDto(Academy academy) {
            return GradeDto.builder().name(this.getSymbol()).tuitionAvg(this.calculateAvgTuition(academy.getCourses())).build();
        }
    },
    ELEMENTARY(Arrays.asList(SpecifiedGrades.ELEMENTARY_ALL, SpecifiedGrades.ELEMENTARY_1, SpecifiedGrades.ELEMENTARY_2, SpecifiedGrades.ELEMENTARY_3, SpecifiedGrades.ELEMENTARY_4, SpecifiedGrades.ELEMENTARY_5, SpecifiedGrades.ELEMENTARY_6), "초등") {
        @Override
        public GradeDto generateGradeDto(Academy academy) {
            return GradeDto.builder().name(this.getSymbol()).tuitionAvg(this.calculateAvgTuition(academy.getCourses())).build();
        }
    },
    MIDDLE(Arrays.asList(SpecifiedGrades.MIDDLE_ALL, SpecifiedGrades.MIDDLE_1, SpecifiedGrades.MIDDLE_2, SpecifiedGrades.MIDDLE_3), "중등") {
        @Override
        public GradeDto generateGradeDto(Academy academy) {
            return GradeDto.builder().name(this.getSymbol()).tuitionAvg(this.calculateAvgTuition(academy.getCourses())).build();
        }
    },
    HIGH(Arrays.asList(SpecifiedGrades.HIGH_ALL, SpecifiedGrades.HIGH_1, SpecifiedGrades.HIGH_2, SpecifiedGrades.HIGH_3), "고등") {
        @Override
        public GradeDto generateGradeDto(Academy academy) {
            return GradeDto.builder().name(this.getSymbol()).tuitionAvg(this.calculateAvgTuition(academy.getCourses())).build();
        }
    };

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

    public long calculateAvgTuition(List<Course> courses) {
        return ((long) courses.stream().filter(c -> this.isContainingGrade(c.getGrades())).mapToLong(c -> c.getTuition()).average().orElse(0L));
    }

    private boolean isContainingGrade(SpecifiedGrades grade) {
        return this.specifiedGrades.stream().anyMatch(g -> g == grade);
    }

    public enum SpecifiedGrades {
        GRADE_UNDEF("미분류"),
        PRESCHOOL_ALL(""),
        ELEMENTARY_ALL("초등"),
        ELEMENTARY_1("초등1"),
        ELEMENTARY_2("초등2"),
        ELEMENTARY_3("초등3"),
        ELEMENTARY_4("초등4"),
        ELEMENTARY_5("초등5"),
        ELEMENTARY_6("초등6"),
        MIDDLE_ALL("중등"),
        MIDDLE_1("중등1"),
        MIDDLE_2("중등2"),
        MIDDLE_3("중등3"),
        HIGH_ALL("고등"),
        HIGH_1("고등1"),
        HIGH_2("고등2"),
        HIGH_3("고등3");

        private String symbol;

        SpecifiedGrades(String symbol) {
            this.symbol = symbol;
        }

        @JsonValue
        public String getSymbol() {
            return this.symbol;
        }

        public static SpecifiedGrades getGradesByString(String symbol) {
            if(symbol.isEmpty() || symbol == null) {
                return GRADE_UNDEF;
            }
            return Arrays.stream(SpecifiedGrades.values()).filter(g -> symbol.equals(g.getSymbol())).findAny().orElse(GRADE_UNDEF);
        }

    }

}
