package com.zfconsulting.pochateoas.controller;

import com.zfconsulting.pochateoas.model.Customer;
import com.zfconsulting.pochateoas.service.CustomerService;
import com.zfconsulting.pochateoas.service.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/{customerId}", produces = "application/vnd.amundsen-uber+json")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerDetail(customerId);

        return  Optional.of(customer)
                .map(customer1 -> EntityModel.of(customer1,
                        linkTo(methodOn(OrderController.class).getOrderById(customerId)).withRel("Order"),
                        linkTo(methodOn(CustomerController.class).getCustomerById(customerId)).withSelfRel()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
