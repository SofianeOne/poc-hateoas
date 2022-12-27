package com.zfconsulting.pochateoas.service;

import com.zfconsulting.pochateoas.controller.CustomerController;
import com.zfconsulting.pochateoas.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/orders")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<EntityModel<Order>> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);

        return Optional.of(order)
                .map(order1 -> EntityModel.of(order1,
                        linkTo(methodOn(CustomerController.class).getCustomerById(orderId)).withRel("customer-detail"),
                        linkTo(methodOn(OrderController.class).getOrderById(orderId)).withSelfRel()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
