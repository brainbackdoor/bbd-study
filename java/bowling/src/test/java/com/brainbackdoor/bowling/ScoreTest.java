package com.brainbackdoor.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    @Test
    public void score_show() {
        Score score = new Score();
        score.add(new Frame(1).builder().first(8).second(2).build());
        score.add(new Frame(2).builder().first(8).second(2).build());
        score.add(new Frame(3).builder().first(8).second(2).build());
        score.add(new Frame(4).builder().first(8).second(2).build());
        System.out.println(score);
    }

    @Test
    public void frame_result() {
        Score score = new Score();
        score.add(new Frame(1).builder().first(8).second(2).build());

    }
}