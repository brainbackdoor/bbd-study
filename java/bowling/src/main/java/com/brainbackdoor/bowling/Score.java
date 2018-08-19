package com.brainbackdoor.bowling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Score {
    private static final int MAX = 10;
    List<Frame> frames = new ArrayList();

    public void add(Frame frame) {
        frames.add(frame);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX; i++) {
            sb.append("  ").append(frames.get(i).getResult()).append(" |");
        }
        return sb.toString();
    }

    public String getScore() {
        StringBuilder sb = new StringBuilder();
        int totalPoint = 0;
        for (int i = 0; i < MAX; i++) {
            totalPoint += getPoint(i);
            if(totalPoint < 10 )  sb.append("  ").append(totalPoint).append("   |");
            else if(totalPoint > 100 )  sb.append("  ").append(totalPoint).append(" |");
            else sb.append("  ").append(totalPoint).append("  |");
        }
        return sb.toString();
    }

    public int getPoint(int i) {
        int point = frames.get(i).getPoint();
        if (frames.get(i).isStrike()) point += frames.get(i + 1).getPoint() + frames.get(i + 2).getPoint();
        if (frames.get(i).isSpare()) point += frames.get(i + 1).getPoint();
        return point;
    }

    public int getSize() {
        return MAX;
    }

    public boolean isStike() {
        return frames.get(9).isStrike();
    }

    public boolean isSpare() {
        return frames.get(9).isSpare();
    }

}
