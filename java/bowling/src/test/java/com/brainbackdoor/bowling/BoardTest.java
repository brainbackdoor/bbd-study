package com.brainbackdoor.bowling;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

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
        assertEquals("X  ",board.check(10));
    }

    @Test
    public void spare_show() {
        assertEquals("9|/", board.check(9));
    }

}