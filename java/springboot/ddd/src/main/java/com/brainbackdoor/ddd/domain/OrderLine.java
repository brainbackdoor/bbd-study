package com.brainbackdoor.ddd.domain;

public class OrderLine {
    private Product product;
    private int price;
    private int quantity;
    int amounts;

    public OrderLine(Product product, int price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private int calculateAmounts() {
        return price * quantity;
    }

    public int getAmounts() {
        return amounts;
    }
}
