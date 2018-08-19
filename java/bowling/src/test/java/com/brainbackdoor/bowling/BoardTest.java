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

    /*
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  LDG |  0|4 |  9|0 |  5|4 |  1|0 |  0|5 |  0|/ |  3|2 |  0|4 |  X   |  1|3 |
    이 경우 결과는 어떻게 나오는 것이 맞는가?
     */

    @Test
    public void board_show() throws Exception {
        board.game();
        System.out.println(board.frame());
        System.out.println(board.resultShow());
        System.out.println(board.scoreShow());
    }
}