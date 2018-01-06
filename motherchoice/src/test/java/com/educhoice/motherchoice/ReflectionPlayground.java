package com.educhoice.motherchoice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReflectionPlayground {

    private Object obj;

    @Before
    public void setUp() {
        this.obj = "헬로월드";
    }

    @Test
    public void 클래스정보_받아오기실험() {
        assertEquals("String", this.obj.getClass().getSimpleName());
    }
}
