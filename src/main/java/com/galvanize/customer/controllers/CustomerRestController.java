package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {

    @Autowired
    CustomerService service;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> findCustomersByState(@RequestParam String state){
        return service.findByState(state);
    }

    @GetMapping("/all")
    public List<Customer> findAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/{custId}")
    public Customer findById(@PathVariable Long custId){
        return service.getCustomer(custId);
    }
}
