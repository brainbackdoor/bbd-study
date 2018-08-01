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
        return "Score{" +
                "frames=" + frames +
                '}';
    }
}
