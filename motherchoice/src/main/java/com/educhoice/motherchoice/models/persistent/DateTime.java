package com.educhoice.motherchoice.models.persistent;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
public class DateTime {

    @Transient
    private static final Logger log = LoggerFactory.getLogger(DateTime.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dateTimeId;

    private Date date;

    public DateTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(date);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
