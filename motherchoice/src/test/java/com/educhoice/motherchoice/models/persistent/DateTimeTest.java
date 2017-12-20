package com.educhoice.motherchoice.models.persistent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTimeTest {

    private DateTime time;

    @Before
    public void setUp() {
        this.time = new DateTime("2017-07-07");
    }

    @Test
    public void 날짜_잘나오는지() {
        assertEquals("2017-07-07", time.getDate().toString());
    }

}