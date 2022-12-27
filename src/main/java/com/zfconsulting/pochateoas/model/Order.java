package com.zfconsulting.pochateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class Order extends RepresentationModel<Order> {
    private String orderId;
    private double price;
    private int quantity;

    public Order(final String orderId, final double price, final int quantity) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

