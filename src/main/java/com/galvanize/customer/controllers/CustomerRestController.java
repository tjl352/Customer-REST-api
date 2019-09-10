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

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer c){
        return service.addCustomer(c);
    }

    //@GetMapping("/customers")
    @RequestMapping("/customers")
    public List<Customer> getCustomersByState(@RequestParam(value = "state", required = true) String state){
        return service.getCustomersByState(state);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }

    @PutMapping("/customer")
    public Customer updateCustomerPhoneNumber(@RequestBody Customer c){
        return service.updateCustomerPhoneNumber(c);
    }
}
