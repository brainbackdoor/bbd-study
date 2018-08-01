package com.brainbackdoor.bowling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Player {
    public static final int MAX = 10;
    Score score;
    String name;
    Player(String name) {
        this.name = name;
    }
    public int bowling() {
        return (int) (Math.random()*(MAX + 1));
    }
}
