package com.example.model;

import com.example.model.Grades.SpecifiedGrades;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "courseid")
    private List<DateTime> dateTime;

    @Enumerated(EnumType.ORDINAL)
    private SpecifiedGrades grades;

    @Enumerated(EnumType.STRING)
    private CoursesClassification.SpecifiedCoursesClassification coursesClassification;

    private String title;

    private boolean regularCourse;
    private long tuition;

    //TODO add business logics for Course modification / courses open time calculation.

    public enum CoursesClassification {

        INTEGRATED("종합", Arrays.asList(SpecifiedCoursesClassification.UNDEF)),
        KOREAN("국어", null),
        FOREIGN_LANGUAGE("외국어", Arrays.asList(SpecifiedCoursesClassification.ENGLISH, SpecifiedCoursesClassification.CHINESE, SpecifiedCoursesClassification.JAPANESE, SpecifiedCoursesClassification.ENGLISH_CERTIFICATE)),
        MATH("수학", Arrays.asList(SpecifiedCoursesClassification.MATH, SpecifiedCoursesClassification.MATH_HUMANITIES, SpecifiedCoursesClassification.MATH_NATURAL, SpecifiedCoursesClassification.MATH_THINKING)),
        SOCIAL_STUDIES("사회", Arrays.asList(SpecifiedCoursesClassification.SOCIAL_STUDIES, SpecifiedCoursesClassification.SOCIAL_STUDIES_CULTURRAL_STUDIES, SpecifiedCoursesClassification.SOCIAL_STUDIES_EAST_ASIAN_STUDIES, SpecifiedCoursesClassification.SOCIAL_STUDIES_ECONOMICS, SpecifiedCoursesClassification.SOCIAL_STUDIES_ETHICS, SpecifiedCoursesClassification.SOCIAL_STUDIES_CULTURRAL_STUDIES, SpecifiedCoursesClassification.SOCIAL_STUDIES_KOREAN_GEOGRAPHY, SpecifiedCoursesClassification.SOCIAL_STUDIES_KOREAN_HISTORY,
                SpecifiedCoursesClassification.SOCIAL_STUDIES_ECONOMICS, SpecifiedCoursesClassification.SOCIAL_STUDIES_IDEOLOGY, SpecifiedCoursesClassification.SOCIAL_STUDIES_WORLD_HISTORY, SpecifiedCoursesClassification.SOCIAL_STUDIES_WORLD_GEOGRAPHY, SpecifiedCoursesClassification.SOCIAL_STUDIES_LAW_POLITICS)),
        SCIENCE("과학", Arrays.asList(SpecifiedCoursesClassification.SCIENCE, SpecifiedCoursesClassification.SCIENCE_BIOLOGY_I, SpecifiedCoursesClassification.SCIENCE_BIOLOGY_II, SpecifiedCoursesClassification.SCIENCE_CHEMISTRY_I, SpecifiedCoursesClassification.SCIENCE_CHEMISTRY_II,
        SpecifiedCoursesClassification.SCIENCE_EARTH_I,SpecifiedCoursesClassification.SCIENCE_EARTH_II, SpecifiedCoursesClassification.SCIENCE_PHYSICS_I, SpecifiedCoursesClassification.SCIENCE_PHYSICS_II)),
        ESSAY("논술", Arrays.asList(SpecifiedCoursesClassification.ESSAY)),
        ART_PE("예체능", Arrays.asList(SpecifiedCoursesClassification.ARTPE_ART, SpecifiedCoursesClassification.ARTPE_ENTERTAINMENT, SpecifiedCoursesClassification.ARTPE_MUSIC, SpecifiedCoursesClassification.ARTPE_PE));

        private String symbol;
        private List<SpecifiedCoursesClassification> specificCourses;


        CoursesClassification(String symbol, List<SpecifiedCoursesClassification> specificCourses) {
            this.symbol = symbol;
            this.specificCourses = specificCourses;
        }

        public static CoursesClassification getCoursesClassificationBySymbol(String symbol) {
            return Arrays.stream(CoursesClassification.values()).filter(course -> course.isCorrectSymbol(symbol)).findAny().orElse(CoursesClassification.INTEGRATED);
        }

        private boolean isCorrectSymbol(String symbol) {
            return symbol.equals(this.symbol);
        }

        public enum SpecifiedCoursesClassification {
            UNDEF(""),
            ENGLISH("영어"),
            ENGLISH_CERTIFICATE("영어인증시험"),
            CHINESE("중국어"),
            JAPANESE("일본어"),
            CHINESE_CHARACTER("한자"),
            MATH("일반수학"),
            MATH_HUMANITIES("문과수학"),
            MATH_NATURAL("이과수학"),
            MATH_THINKING("사고력"),
            SOCIAL_STUDIES("일반사회"),
            SOCIAL_STUDIES_KOREAN_HISTORY("한국사"),
            SOCIAL_STUDIES_ETHICS("생활윤리"),
            SOCIAL_STUDIES_IDEOLOGY("윤리사상"),
            SOCIAL_STUDIES_EAST_ASIAN_STUDIES("동아시아사"),
            SOCIAL_STUDIES_WORLD_HISTORY("세계사"),
            SOCIAL_STUDIES_KOREAN_GEOGRAPHY("한국지리"),
            SOCIAL_STUDIES_WORLD_GEOGRAPHY("세계지리"),
            SOCIAL_STUDIES_LAW_POLITICS("법과정치"),
            SOCIAL_STUDIES_ECONOMICS("경제"),
            SOCIAL_STUDIES_CULTURRAL_STUDIES("사회문화"),
            SCIENCE("과학"),
            SCIENCE_PHYSICS_I("물리1"),
            SCIENCE_PHYSICS_II("물리2"),
            SCIENCE_CHEMISTRY_I("화학1"),
            SCIENCE_CHEMISTRY_II("화학2"),
            SCIENCE_BIOLOGY_I("생명과학1"),
            SCIENCE_BIOLOGY_II("생명과학2"),
            SCIENCE_EARTH_I("지구과학"),
            SCIENCE_EARTH_II("지구과학2"),
            ESSAY("논술"),
            ARTPE_PE("체육"),
            ARTPE_MUSIC("음악"),
            ARTPE_ART("미술"),
            ARTPE_ENTERTAINMENT("에능");

            private String symbol;

            SpecifiedCoursesClassification(String symbol) {
                this.symbol = symbol;
            }

            private boolean isCorrectSymbol(String symbol) {
                return symbol.equals(this.symbol);
            }

            public static SpecifiedCoursesClassification getSpecifiedCoursesClassificationBySymbol(String symbol) {
                return Arrays.stream(SpecifiedCoursesClassification.values()).filter(classification -> classification.isCorrectSymbol(symbol)).findAny().orElse(SpecifiedCoursesClassification.UNDEF);
            }

            @JsonValue
            public String getSymbol() {
                return this.symbol;
            }

        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", dateTime=" + dateTime +
                ", grades=" + grades +
                ", coursesClassification=" + coursesClassification +
                ", title='" + title + '\'' +
                ", regularCourse=" + regularCourse +
                ", tuition=" + tuition +
                '}';
    }
}
