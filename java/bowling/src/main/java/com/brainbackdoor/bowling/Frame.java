package com.brainbackdoor.bowling;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frame {
    private int turn;
    private int first;
    private int second;

    @Getter
    @AllArgsConstructor
    public enum Status {
        STRIKE("STRIKE"),
        SPARE("SPARE"),
        OPEN_FRAME("OPEN_FRAME");
        private String symbol;
    }

    public Frame (int turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "turn=" + turn +
                ", first=" + first +
                ", second=" + second +
                '}';
    }

    boolean isSpare() {
        if (!isStrike()) return first + second == 10;
        return false;
    }

    boolean isStrike() {
        return first == 10;
    }

    public Status result() {
        if(isStrike()) return Status.STRIKE;
        if(isSpare()) return Status.SPARE;
        return Status.OPEN_FRAME;
    }
}
