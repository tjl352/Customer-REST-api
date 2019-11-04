package com.galvanize.customer.controllers;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer c){
        return customerService.addCustomer(c);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getOneCustomerById(id);
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer c){
        return customerService.updateCustomer(c);
    }

    @DeleteMapping("/customer")
    public void deleteCustomer(@RequestBody Customer c){
        customerService.deleteCustomer(c);
    }
}
