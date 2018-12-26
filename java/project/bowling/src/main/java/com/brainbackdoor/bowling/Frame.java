package com.brainbackdoor.bowling;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Frame {
    private List<Integer> point = new ArrayList(2);
    private Status status;

    public int getFirst() {
        return point.get(0);
    }

    public int getSecond() {
        return point.get(1);
    }

    public int getPoint(Status status) {
        if((status == Status.STRIKE || status == Status.OPEN_FRAME) && point.size() == 2) return point.get(0) + point.get(1);
        return point.get(0);
    }

    public boolean isNext() {
        return !isStrike();
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        STRIKE("STRIKE"),
        SPARE("SPARE"),
        OPEN_FRAME("OPEN_FRAME");
        private String symbol;
    }
    public Frame point(int point) throws Exception {
        if(status == Status.STRIKE) throw new Exception("첫번째 투구가 스트라이크 입니다");
        this.point.add(point);
        this.status = setStatus();
        return this;
    }

    boolean isSpare() {
        if (!isStrike() && point.size() == 2) return point.get(0) + point.get(1) == 10;
        return false;
    }

    boolean isStrike() {
        return point.get(0) == 10;
    }

    Status setStatus() {
        if(isStrike()) return Status.STRIKE;
        if(isSpare()) return Status.SPARE;
        return Status.OPEN_FRAME;
    }

    public String getResult() {
        if(status == Status.STRIKE) return "X  ";
        if(status == Status.SPARE) return point.get(0) + "|/";
        return point.get(0) + "|" + point.get(1);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "point=" + point +
                ", status=" + status +
                '}';
    }
}
