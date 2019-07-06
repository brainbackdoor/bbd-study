package com.brainbackdoor.object2;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runnigTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runnigTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runnigTime = runnigTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
