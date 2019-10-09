package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service @Component
public class CustomerService {

    //@Autowired
    CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.repository = customerRepository;
    }

    public Customer addCustomer(Customer c){
        return repository.save(c);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public List<Customer> getCustomersByState(String state) {
        return repository.getCustomersByState(state);
    }

    public Customer getCustomerById(Long id){
        return repository.findById(id).get();
    }

    public Customer updateCustomerPhoneNumber(Customer c) {
        return repository.save(c);
    }
}
