package com.brainbackdoor.tdd;

public class Franc extends Money {
    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    public String currency() {
        return currency;
    }
}
