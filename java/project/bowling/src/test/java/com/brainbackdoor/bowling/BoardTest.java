package com.brainbackdoor.bowling;

import org.junit.Before;
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
        assertEquals("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |", board.frame());
    }

    @Test
    public void board_show() throws Exception {
        board.game();
        System.out.println(board.frame());
        System.out.println(board.resultShow());
        System.out.println(board.scoreShow());
    }
}