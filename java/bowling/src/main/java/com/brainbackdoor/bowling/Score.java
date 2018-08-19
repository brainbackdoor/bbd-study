package com.brainbackdoor.bowling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Score {
    private static final int MAX = 10;
    List<Frame> frames = new ArrayList(MAX);

    public void add(Frame frame) {
        frames.add(frame);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        frames.stream().forEach(v -> sb.append("  ").append(v.getResult()).append(" |"));
        return sb.toString();
    }

    public int getSize() {
        return MAX;
    }

    public boolean isChance(){
        if(frames.get(9).isStrike() || frames.get(9).isSpare()) return true;
        return false;
    }




}
