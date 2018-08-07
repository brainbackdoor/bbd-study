package com.brainbackdoor.bowling;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.input("LDG");
    }

    @Test
    public void player_input_show() {
        board.print();
    }

    @Test
    public void frame_show() {
        board.frame();
    }

    @Test
    @Ignore
    public void game_show_first_step() {
        assertEquals("|  LDG |      |      |      |      |      |      |      |      |      |      |", board.game());
    }

    @Test
    public void strike_show() {
        assertEquals("X  ",board.result(10));
    }

    @Test
    public void spare_show() {
        assertEquals("9|/", board.result(9));
    }

}