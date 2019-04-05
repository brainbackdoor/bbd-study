package com.brainbackdoor.tdd;

public class Money {
    protected int amount;

    public boolean equals(Object o) {
        Money money = (Money) o;
        return amount == money.amount;
    }
}
