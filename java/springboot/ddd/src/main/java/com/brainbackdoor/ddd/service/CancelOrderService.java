package com.brainbackdoor.ddd.service;

import com.brainbackdoor.ddd.domain.Order;
import org.springframework.transaction.annotation.Transactional;

public class CancelOrderService {
    @Transactional
    public void cancelOrder(String orderId) throws OrderNotFoundException {
        Order order = findOrdeById(orderId);
        if(order == null) throw new OrderNotFoundException(orderId);
        order.cancel();
    }

    private Order findOrdeById(String orderId) {
        return null;
    }
}
