package com.brainbackdoor.bowling;

import lombok.Getter;

@Getter
public class Player {
    public static final int MAX = 10;
    Score score = new Score();
    String name;

    Player(String name) {
        this.name = name;
    }

    public void bowling() throws Exception {
        int p = random(MAX);
        Frame frame = new Frame().point(p);
        if(frame.isNext()) frame.point(random(MAX - p));
        score.add(frame);
    }

    private int random(int max) {
        return (int) (Math.random() * (max + 1));
    }

    public String getScore() {
        return score.toString();
    }

    public int getMaxTurn() {
        return score.getSize();
    }

    public boolean isMoreThrow() {
        return score.isChance();
    }
}
