package com.educhoice.motherchoice.valueobject.models.query;

import com.educhoice.motherchoice.models.persistent.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter

public class SearchableDateTime {

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(DateTime.class);

    @JsonIgnore
    private static final SimpleDateFormat format = new SimpleDateFormat("kk:mm");

    private List<DateTime.WeekDays> day;
    private String startTime;
    private String endTime;


    public List<DateTime> generateDateTimefromDto() {
        return this.day.stream().map(d -> new DateTime(this.startTime, this.endTime, d.getSymbol())).collect(Collectors.toList());
    }

    private static long getTimeFromString(String time) {
        try {
            return format.parse(time).getTime();
        } catch (Exception e) {
            log.error(e.getMessage());
            return 1L;
        }
    }

    public LocalTime generateStartTime() {
        return LocalTime.parse(this.startTime);
    }

    public LocalTime generateEndTime() {
        return LocalTime.parse(this.endTime);
    }

    @Override
    public String toString() {
        return "SearchableDateTime{" +
                "days=" + day +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
