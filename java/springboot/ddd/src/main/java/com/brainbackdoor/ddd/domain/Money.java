package com.brainbackdoor.ddd.domain;

import java.util.stream.IntStream;

public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
