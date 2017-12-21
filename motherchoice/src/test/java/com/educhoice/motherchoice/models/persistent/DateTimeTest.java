package com.educhoice.motherchoice.models.persistent;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class DateTimeTest {

    private static final Logger log = LoggerFactory.getLogger(DateTimeTest.class);

    private DateTime time;

    @Before
    public void setUp() {
        this.time = new DateTime("19:30", "21:30", "목");
    }

    @Test
    public void 날짜_잘나오는지() {
        log.info(this.time.toString());
        assertEquals("21:30:00", this.time.getEndTime().toString());
    }

}