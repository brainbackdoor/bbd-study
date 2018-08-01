package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player = new Player("LDG");

    @Test
    public void onerandom_bowling() {
        System.out.println(player.bowling());
    }

    @Test
    public void player_name() {
        assertEquals("LDG", player.getName());
    }


}