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
}
