package com.brainbackdoor.ddd.domain;

import java.util.List;

public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    private List<OrderLine> orderLines;
    private int totalAmounts;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null)
            throw new IllegalArgumentException("no ShippingInfo");
        this.shippingInfo = shippingInfo;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream()
                .mapToInt(x -> x.getAmounts()).sum()).getValue();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {

    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        this.shippingInfo = newShippingInfo;
    }

    private void verifyNotYetShipped() {
        if (state != OrderState.PAYMENT_WAITING || state != OrderState.PREPARING)
            throw new IllegalStateException("already shipped");
    }

    public void changeShipped() {
        this.state = OrderState.SHIPPED;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELD;
    }

    public void completePayment() {
        //TODO: 결제완료로 변경하기
    }

}


