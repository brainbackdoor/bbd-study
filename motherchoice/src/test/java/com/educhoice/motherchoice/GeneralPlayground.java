package com.educhoice.motherchoice;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneralPlayground {

    private static final Logger log = LoggerFactory.getLogger(GeneralPlayground.class);

    private Object obj;
    private int limit = 100000000;

    @Before
    public void setUp() {
        this.obj = "헬로월드";
    }

    @Test
    public void 스트링빌더_속도차이() {
        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();

        new Thread(() -> {
            long start = System.currentTimeMillis();

            for (int i = limit; i --> 0;) {
                builder.append("a");
            }
            log.debug("elapsed time for builder : {}ms", System.currentTimeMillis() - start);
        }).run();

        new Thread(() -> {
            long start = System.currentTimeMillis();

            for (int i = limit; i --> 0;) {
                buffer.append("a");
            }
            log.debug("elapsed time for buffer :{}ms", System.currentTimeMillis() - start);
        }).run();

    }
}
