package com.brainbackdoor.bowling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Score {

    List<Frame> frames = new ArrayList();

    public void add(Frame frame) {
        frames.add(frame);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frames.size(); i++) {
            sb.append("  ").append(frames.get(i).getResult()).append(" |");
        }
        return sb.toString();
    }

    public int getPoint(int i) {
        int point = frames.get(i).getPoint(Frame.Status.OPEN_FRAME);
        if (frames.get(i).isStrike()) point += frames.get(i + 1).getPoint(Frame.Status.STRIKE);
        if (frames.get(i).isSpare()) point += frames.get(i + 1).getPoint(Frame.Status.SPARE);
        return point;
    }

    public boolean isStike() {
        return frames.get(9).isStrike();
    }

    public boolean isSpare() {
        return frames.get(9).isSpare();
    }

}
