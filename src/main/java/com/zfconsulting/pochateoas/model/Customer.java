package com.zfconsulting.pochateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class Customer extends RepresentationModel<Customer> {

    private String customerId;
    private String customerName;
    private String companyName;

    public Customer(final String s, String customerName, String companyName) {
        super();
        this.customerId = s;
        this.customerName = customerName;
        this.companyName = companyName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCompanyName() {
        return companyName;
    }

}
