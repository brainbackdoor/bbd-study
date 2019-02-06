package com.brainbackdoor.ddd.service;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String orderId) {
    }
}
