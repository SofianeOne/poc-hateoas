package com.zfconsulting.pochateoas.service;

import com.zfconsulting.pochateoas.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderService {

    private HashMap<String, Order> orderMap;

    public OrderService() {
        this.orderMap = new HashMap<>();

        final Order orderOne = new Order("10A", 100, 1);
        final Order orderTwo = new Order("20A", 200, 2);
        final Order orderThree = new Order("30A", 300, 3);

        orderMap.put("10A", orderOne);
        orderMap.put("20B", orderTwo);
        orderMap.put("30C", orderThree);
    }

    public Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }
}
