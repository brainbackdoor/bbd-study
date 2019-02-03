package com.brainbackdoor.ddd.domain;

public class ShippingInfo {
    private Receiver receiver;
    private Address address;

    public ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }

    public String getReceiverName() {
        return receiver.getName();
    }

    public String getReceiverPhoneNumber() {
        return receiver.getPhoneNumber();
    }

    public String getShippingAddress1() {
        return address.getAddress1();
    }

    public String getShippingAddress2() {
        return address.getAddress2();
    }


}
