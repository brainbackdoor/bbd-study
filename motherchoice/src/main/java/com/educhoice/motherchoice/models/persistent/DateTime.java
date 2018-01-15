package com.educhoice.motherchoice.models.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Objects;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;

@Embeddable
@Getter
public class DateTime implements Serializable {

    @Transient
    private static final Logger log = LoggerFactory.getLogger(DateTime.class);

    @Transient
    private static final SimpleDateFormat format = new SimpleDateFormat("kk:mm");

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @Enumerated(value = EnumType.STRING)
    private WeekDays day;

    public enum WeekDays {
        UNDEF(""),
        MON("월"),
        TUE("화"),
        WED("수"),
        THU("목"),
        FRI("금"),
        SAT("토"),
        SUN("일");

        private String symbol;

        WeekDays(String symbol) {
            this.symbol = symbol;
        }

        @JsonValue
        public String getSymbol() {
            return this.symbol;
        }

        public static WeekDays getWeekDaysBySymbol(String symbol) {
            return Arrays.stream(WeekDays.values()).filter(days -> days.isMatchingSymbol(symbol)).findAny().orElse(WeekDays.UNDEF);
        }

        private boolean isMatchingSymbol(String symbol) {
            return symbol.equals(this.symbol);
        }

        @Override
        public String toString() {
            return this.symbol;
        }
    }

    private DateTime() {
    }

    public DateTime(String startTime, String endTime, String weekdays) {
        this.startTime = getTimeFromString(startTime);
        this.endTime = getTimeFromString(endTime);
        this.day = WeekDays.getWeekDaysBySymbol(weekdays);

    }

    private static LocalTime getTimeFromString(String time) {
        return LocalTime.parse(time);
    }

    @Override
    public String toString() {
        StringBuilder stringObject = new StringBuilder();

        stringObject.append(this.day.toString());
        stringObject.append(this.startTime.toString());
        stringObject.append(this.endTime.toString());

        return stringObject.toString();
    }
}
