package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void oneFrame() throws Exception {
        int first = 8;
        Frame frame = new Frame().point(first).point(2);
        System.out.println(frame);
        assertEquals(first, frame.getFirst());
    }

    @Test
    public void frame_strike_check() throws Exception {
        int strke = 10;

        Frame frame = new Frame().point(strke);
        assertEquals(true, frame.isStrike());
    }

    @Test
    public void frame_spare_check() throws Exception {
        int first = 8;
        int second = 2;

        Frame frame = new Frame().point(first).point(second);
        assertEquals(true, frame.isSpare());

        first = 10;
        frame = new Frame().point(first);
        assertEquals(false, frame.isSpare());
    }

    @Test(expected = Exception.class)
    public void strike_exception() throws Exception {
        int first = 10;
        int second = 2;

        Frame frame = new Frame().point(first).point(second);
        assertEquals(true, frame.isSpare());
    }

    @Test
    public void frame_result() throws Exception {
        int strke = 10;

        Frame frame = new Frame().point(strke);
        assertEquals(Frame.Status.STRIKE, frame.setStatus());

        int first = 8;
        int second = 2;

        frame = new Frame().point(first).point(second);
        assertEquals(Frame.Status.SPARE, frame.setStatus());

        second = 1;
        frame = new Frame().point(first).point(second);
        assertEquals(Frame.Status.OPEN_FRAME, frame.setStatus());
    }
}