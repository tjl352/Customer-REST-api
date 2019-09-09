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

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer c){
        return service.addCustomer(c);
    }

    @GetMapping("/all/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state){
        return service.getCustomersByState(state);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }

    @PutMapping("/update")
    public Customer updateCustomerPhoneNumber(@RequestBody Customer c){
        return service.updateCustomerPhoneNumber(c);
    }
}
