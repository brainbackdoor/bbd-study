package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {

    @Test
    public void oneFrame() {
        int first = 8;
        Frame frame = new Frame(1).builder().first(first).second(2).build();
        System.out.println(frame);
        assertEquals(first, frame.getFirst());
    }

    @Test
    public void frame_strike_check() {
        int strke = 10;
        int turn = 1;

        Frame frame = new Frame(turn).builder().first(strke).build();
        assertEquals(true, frame.isStrike());
    }

    @Test
    public void frame_spare_check() {
        int first = 8;
        int second = 2;
        int turn = 1;

        Frame frame = new Frame(turn).builder().first(first).second(second).build();
        assertEquals(true, frame.isSpare());

        first = 10;
        turn = 2;
        frame = new Frame(turn).builder().first(first).build();
        assertEquals(false, frame.isSpare());
    }

    @Test
    public void frame_result() {
        int strke = 10;
        int turn = 1;

        Frame frame = new Frame(turn).builder().first(strke).build();
        assertEquals(Frame.Status.STRIKE, frame.result());

        turn = 2;
        int first = 8;
        int second = 2;

        frame = new Frame(turn).builder().first(first).second(second).build();
        assertEquals(Frame.Status.SPARE, frame.result());

        turn = 3;
        second = 1;
        frame = new Frame(turn).builder().first(first).second(second).build();
        assertEquals(Frame.Status.OPEN_FRAME, frame.result());
    }
}