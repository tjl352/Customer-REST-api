package com.galvanize.customer.services;

import com.galvanize.customer.entities.Customer;
import com.galvanize.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Component
public class CustomerService {

    @Autowired
    CustomerRepository repository;


    public Customer addCustomer(Customer c) {
        return repository.save(c);
    }

    public List<Customer> findByState(String state) {
        return repository.findCustomersByState(state);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(long customerId) {
        return repository.findById(customerId).get();
    }

    public void update(Customer customer) {
        repository.save(customer);
    }
}
