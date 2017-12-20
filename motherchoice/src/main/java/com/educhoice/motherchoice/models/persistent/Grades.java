package com.educhoice.motherchoice.models.persistent;

public enum Grades {

    PRESCHOOL("유아"),
    ELEMENTARY("초"),
    MIDDLE("중"),
    HIGH("고");

    private SpecifiedGrades specifiedGrade;
    private String symbol;

    Grades(String symbol) {
        this.symbol = symbol;
    }

    public enum SpecifiedGrades {
        ELEMENTARY_1("1"),
        ELEMENTARY_2("2"),
        ELEMENTARY_3("3"),
        ELEMENTARY_4("4"),
        ELEMENTARY_5("5"),
        ELEMENTARY_6("6"),
        MIDDLE_1("1"),
        MIDDLE_2("2"),
        MIDDLE_3("3"),
        HIGH_1("1"),
        HIGH_2("2"),
        HIGH_3("3");

        private String symbol;

        SpecifiedGrades(String symbol) {
            this.symbol = symbol;
        }
    }

}
