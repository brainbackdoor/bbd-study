package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Entity
@Getter
public class DateTime {

    @Transient
    private static final Logger log = LoggerFactory.getLogger(DateTime.class);

    @Transient
    private static final SimpleDateFormat format = new SimpleDateFormat("kk:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dateTimeId;

    private Time startTime;
    private Time endTime;

    @Enumerated(value = EnumType.ORDINAL)
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
        this.startTime = new Time(getTimeFromString(startTime));
        this.endTime = new Time(getTimeFromString(endTime));
        this.day = WeekDays.getWeekDaysBySymbol(weekdays);

    }

    private static long getTimeFromString(String time) {
        try {
            return format.parse(time).getTime();
        } catch (Exception e) {
            log.error(e.getMessage());
            return 1L;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringObject = new StringBuilder();

        stringObject.append(this.day.toString());
        stringObject.append(String.format("  %s ~ %s", format.format(this.startTime), format.format(this.endTime)));

        return stringObject.toString();
    }
}
