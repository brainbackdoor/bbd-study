package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    @Test
    public void score_show() throws Exception {
        Score score = new Score();
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(10));
        System.out.println(score);
    }

    @Test
    public void last_strike() throws Exception {
        Score score = new Score();
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(10));
        System.out.println(score);
        assertEquals(true, score.isChance());
    }

    @Test
    public void last_spare() throws Exception {
        Score score = new Score();
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(1));
        score.add(new Frame().point(8).point(2));
        System.out.println(score);
        assertEquals(true, score.isChance());
    }
}