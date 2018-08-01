package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void oneFrame() {
        Frame frame = new Frame(1).builder().first(8).second(2).build();
        System.out.println(frame);
    }
}